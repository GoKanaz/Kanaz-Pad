# Contributing to Kanaz Pad

First off, thank you for considering contributing to Kanaz Pad! 🎉

## Code of Conduct

This project and everyone participating in it is governed by our Code of Conduct. By participating, you are expected to uphold this code.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates.

**When reporting a bug, please include:**
- A clear and descriptive title
- Steps to reproduce the behavior
- Expected behavior
- Actual behavior
- Screenshots (if applicable)
- Device information (Android version, device model)
- App version

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

- A clear and descriptive title
- A detailed description of the proposed feature
- Examples of how the feature would be used
- Why this enhancement would be useful

### Pull Requests

1. **Fork the repository** and create your branch from `develop`
   ```bash
   git checkout -b feature/amazing-feature
   ```

2. **Make your changes**
   - Follow the coding style used throughout the project
   - Write clear, concise commit messages
   - Add tests if applicable
   - Update documentation as needed

3. **Test your changes**
   ```bash
   ./gradlew test
   ./gradlew lintDebug
   ```

4. **Commit your changes**
   ```bash
   git commit -m "Add amazing feature"
   ```

5. **Push to your fork**
   ```bash
   git push origin feature/amazing-feature
   ```

6. **Open a Pull Request**
   - Provide a clear description of your changes
   - Reference any related issues
   - Ensure all CI checks pass

## Development Setup

### Prerequisites

- Android Studio Hedgehog or newer
- JDK 17 or newer
- Android SDK (API 24-34)
- Git

### Setup Instructions

1. Clone the repository
   ```bash
   git clone https://github.com/GoKanaz/kanaz-pad.git
   cd kanaz-pad
   ```

2. Open in Android Studio
   - File → Open → Select the project directory

3. Sync Gradle
   - Wait for Gradle sync to complete

4. Run the app
   - Select a device/emulator
   - Click Run (▶️)

## Coding Style

### Kotlin

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Keep functions small and focused
- Add comments for complex logic

### XML

- Use proper indentation (4 spaces)
- Use meaningful IDs
- Follow Material Design guidelines

### Git Commit Messages

- Use present tense ("Add feature" not "Added feature")
- Use imperative mood ("Move cursor to..." not "Moves cursor to...")
- Limit the first line to 72 characters
- Reference issues and pull requests

Example:
```
Add export to PDF feature

- Implement PDF generation using iText
- Add UI controls for export
- Add unit tests

Fixes #123
```

## Project Structure

```
app/src/main/
├── java/com/gokanaz/kanazpad/
│   ├── data/           # Data layer (models, database, repository)
│   ├── ui/             # UI layer (activities, fragments, adapters)
│   ├── utils/          # Utility classes
│   └── MainActivity.kt
└── res/
    ├── layout/         # XML layouts
    ├── values/         # Strings, colors, themes
    ├── drawable/       # Icons and images
    └── menu/           # Menu resources
```

## Testing

### Running Tests

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run lint checks
./gradlew lintDebug
```

### Writing Tests

- Write unit tests for business logic
- Write UI tests for critical user flows
- Aim for meaningful test coverage
- Use clear, descriptive test names

## Documentation

- Update README.md if you change functionality
- Add/update KDoc comments for public APIs
- Update CHANGELOG.md for notable changes

## Release Process

1. Update version in `gradle.properties`
2. Update CHANGELOG.md
3. Create a release branch
4. Test thoroughly
5. Create a pull request to `main`
6. After merge, create a git tag
7. GitHub Actions will build and create release

## Questions?

Feel free to open an issue with the label `question` or contact @GoKanaz

## Recognition

Contributors will be recognized in the README.md file.

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing to Kanaz Pad! 🚀
