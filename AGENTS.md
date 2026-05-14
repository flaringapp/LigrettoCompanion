# Ligretto Companion Agent Notes

## Maintenance
- Keep this file updated when making important project changes that affect future agent work.
- Update these notes when architecture, module structure, build/test commands, tooling, documentation workflow, or major conventions change.
- Keep entries brief and practical; prefer summaries that help the next agent act correctly.

## Project Summary
- This is a Kotlin Multiplatform companion app for the Ligretto board game.
- It targets Android via `:androidApp` and Apple/iOS via the shared `:commonApp` framework.
- UI is Compose Multiplatform with Material 3, Navigation 3, Koin DI, SQLDelight persistence, and multiplatform settings.
- The app tracks games, players, laps, scores, cards left/on-table, end conditions, game resume, settings, and game completion.

## Android CLI
- Use the `android-cli` skill for Android work.
- Prefer the `android` CLI for Android project/device tasks:
  - `android describe --project_dir=.` for project metadata/APK locations.
  - `android run` for deployment when appropriate.
  - `android layout` for inspecting running UI layout.
  - `android screenshot` for device screenshots.
- For Android API/library/best-practice documentation, use `android docs search ...` and `android docs fetch ...` instead of generic web search.
- If `android` fails because it cannot write analytics under `~/.android`, rerun with approval/escalation rather than working around it.

## Module Map
- `:androidApp`: Android application shell, splash/edge-to-edge setup, `LigrettoApp`, and `MainActivity`.
- `:commonApp`: shared app composition, root theme/nav host, DI startup, platform logging, and Apple `MainViewController`.
- `:core:arch`: MVI primitives (`UiState`, `UiIntent`, `UiEffect`, `Store`, `Reducer`, `MviViewModel`) and Compose effect consumption.
- `:core:designsystem`: app theme, color schemes, typography, fonts, and dynamic color platform hooks.
- `:core:ui`: reusable Compose UI components and modifiers.
- `:core:navigation`: shared app navigation primitives and helpers.
- `:core:database`: SQLDelight schema, queries, database drivers, and database DI.
- `:core:settings`: multiplatform settings abstractions and platform factories.
- `:core:di`: shared DI helpers and dispatcher qualifiers.
- `:core:util:common`: small shared utilities used by feature modules.
- `:core:database-test`, `:core:settings-test`, `:core:util:database-test`: test helpers and fixtures for common tests.
- `:feature:home`: aggregate module exporting home UI/domain.
- `:feature:home:ui` and `:feature:home:domain`: home screen and domain logic for starting or resuming games.
- `:feature:game`: aggregate module exporting game UI/data/domain/contracts/model.
- `:feature:game:*`: game model, domain use cases, repository contracts/data, and UI flow.
- `:build-logic`: Gradle convention plugins; prefer these over repeating Gradle configuration in modules.

## Architecture Conventions
- Keep shared business logic in `commonMain`; add platform-specific code only in `androidMain`, `appleMain`, or `nativeMain` when needed.
- Follow the existing feature layering: `model` → `domain-contracts` → `data`/`domain` → `ui`, exported by feature aggregate modules and consumed by `commonApp`.
- UI screens use MVI: `State`, `Intent`, `Effect`, `ViewModel`, plus a route-level composable that collects lifecycle-aware state and delegates rendering to `screen/*Content`.
- Navigation uses Navigation 3, kotlinx-serializable destination objects/classes, and feature-owned `*Navigation.kt` entry providers.
- Koin uses the Koin compiler plugin, not KSP. Apply `ligretto.multiplatform.koin.compiler` for modules that declare annotated definitions.
- Koin modules use annotations (`@Module`, `@Configuration`, `@ComponentScan`, `@Single`, `@Factory`, `@KoinViewModel`) and are discovered through compiler-generated configuration hints.
- Keep Koin module classes internal by default. Only `commonApp` Koin module classes that are exposed through generated APIs during iOS framework export need to be public.
- App startup uses `@KoinApplication` on `LigrettoKoinApp` and `startKoin<LigrettoKoinApp>()` from platform DI entry points.
- `commonApp` enables Koin compile safety; the convention plugin disables compile safety by default for lower modules because cross-module hint analysis is still limited.
- Keep DI module files named after their module classes, for example `DatabaseModule.kt`, `DatabasePlatformModule.android.kt`, `SettingsModule.kt`, `HomeUiModule.kt`, and `GameDomainModule.kt`.
- Keep project convention plugin IDs declared in `gradle/libs.versions.toml`; use `alias(libs.plugins.ligretto...)` in modules and catalog-backed IDs in `build-logic`.
- Database changes belong in SQLDelight `.sq` files under `core/database/src/commonMain/sqldelight`; update mappers/tests when schema or persisted models change.

## Style
- Kotlin style is Android Studio ktlint via `.editorconfig`: 4-space indent, max line length 100, trailing commas enabled, no wildcard imports.
- Prefer focused, immutable data classes and pure reducers/use cases.
- Keep composables small, stable, and preview-friendly; reusable UI should go in `core:ui` or `core:designsystem`.
- Do not introduce Android-only dependencies into shared modules unless they are isolated to Android source sets.

## Useful Commands
- Build Android debug APK: `./gradlew :androidApp:assembleDebug`
- Clean rebuild Android: `./gradlew :androidApp:assembleDebug --rerun-tasks`
- Compile shared iOS simulator app: `./gradlew :commonApp:compileKotlinIosSimulatorArm64`
- Run Android-host/app unit tests: `./gradlew testAndroid testDebugUnitTest`
- Run ktlint checks: `./gradlew ktlintCheck`
- Format Kotlin: `./gradlew ktlintFormat`

## Validation Notes
- There are existing common tests for database queries, settings, game model/domain, and game data.
- DI graph validation is primarily compile-time through the Koin compiler plugin; run `./gradlew :androidApp:assembleDebug` after touching DI.
- When touching SQLDelight, run database and mapper tests where possible.
