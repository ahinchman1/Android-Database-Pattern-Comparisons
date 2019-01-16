# Android Database Pattern Comparisons

## SQLiteOpenHelper

*Implementation*
 * Helper classes manages database creation and easy version management
 * Makes it easy for ContentValue implementations to defer opening and upgrading the database util first use to avoid blocking application startup with long-running databases
 * Not recommended if mobile device has direct access to online databases for security reasons
 * ContentValues saves you from having to write insert statements yourself
 
## SQLDelight
 * generates Kotlin interfaces
 * better tooling/validation than Room - IntelliJ plugin provides high=level features for .sq files like
    - Syntax highlighting
    - Refactoring/finding usages
    - Code autocompletion
    - Generating Queries after editing, compiler editors in the IDE
    