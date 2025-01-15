package uz.droid.wallatopia

sealed class Language(val isoFormat : String, val displayName: String) {
    data object Russian : Language("ru", "Русский")
    data object Uzbek : Language("uz", "O'zbekcha")
    data object English : Language("en", "English")
}