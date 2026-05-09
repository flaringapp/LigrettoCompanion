# Ligretto Companion Agent Notes

## Maintenance
- Keep this file updated when making important project changes that affect future agent work.
- Update these notes when architecture, module structure, build/test commands, tooling, documentation workflow, or major conventions change.
- Keep entries brief and practical; prefer summaries that help the next agent act correctly.

## Project Summary
- This is a Kotlin Multiplatform companion app for the Ligretto board game.
- It targets Android via `:androidApp` and Apple/iOS via the shared `:commonApp` framework.
- UI is Compose Multiplatform with Material 3, type-safe Compose Navigation, Koin DI, SQLDelight persistence, and multiplatform settings.
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
- `:core:database`: SQLDelight schema, queries, database drivers, and database DI.
- `:core:settings`: multiplatform settings abstractions and platform factories.
- `:core:di`: shared DI helpers and dispatcher qualifiers.
- `:feature:home:*`: home screen/domain/DI for starting or resuming games.
- `:feature:game:*`: game model, domain use cases, repository contracts/data, UI flow, and DI.
- `:build-logic`: Gradle convention plugins; prefer these over repeating Gradle configuration in modules.

## Architecture Conventions
- Keep shared business logic in `commonMain`; add platform-specific code only in `androidMain`, `appleMain`, or `nativeMain` when needed.
- Follow the existing feature layering: `model` → `domain-contracts` → `data`/`domain` → `ui` → `di`, wired from `commonApp`.
- UI screens use MVI: `State`, `Intent`, `Effect`, `ViewModel`, plus a route-level composable that collects lifecycle-aware state and delegates rendering to `screen/*Content`.
- Navigation uses kotlinx-serializable destination objects/classes and feature-owned `*Navigation.kt` graph builders.
- Koin uses annotations (`@Module`, `@ComponentScan`, `@Single`, `@Factory`, `@KoinViewModel`) with generated modules; add new modules through feature `di` aggregators and `commonApp` app modules.
- Database changes belong in SQLDelight `.sq` files under `core/database/src/commonMain/sqldelight`; update mappers/tests when schema or persisted models change.

## Style
- Kotlin style is Android Studio ktlint via `.editorconfig`: 4-space indent, max line length 100, trailing commas enabled, no wildcard imports.
- Prefer focused, immutable data classes and pure reducers/use cases.
- Keep composables small, stable, and preview-friendly; reusable UI should go in `core:ui` or `core:designsystem`.
- Do not introduce Android-only dependencies into shared modules unless they are isolated to Android source sets.

## Useful Commands
- Build Android debug APK: `./gradlew :androidApp:assembleDebug`
- Clean rebuild Android: `./gradlew :androidApp:assembleDebug --rerun-tasks`
- Run common/unit tests broadly: `./gradlew allTests`
- Run Android unit tests: `./gradlew :androidApp:testDebugUnitTest`
- Run ktlint checks: `./gradlew ktlintCheck`
- Format Kotlin: `./gradlew ktlintFormat`

## Validation Notes
- There are existing common tests for database queries, settings, game model/domain, and game data.
- There is an Android unit test checking Koin configuration in `commonApp`.
- When touching DI, run a Koin-related test if possible; when touching SQLDelight, run database and mapper tests.
