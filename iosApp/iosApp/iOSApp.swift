import SwiftUI
import commonApp

@main
struct iOSApp: App {

    init() {
        Logging_appleKt.doInitLogging()
        Di_appleKt.doInitDi()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
