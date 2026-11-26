package ru.yandex.practicum.contacts.presentation.country

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yandex.practicum.contacts.R
import ru.yandex.practicum.contacts.data.models.CountryCode
import ru.yandex.practicum.contacts.presentation.ui.components.CommonBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeBottomSheet(
    selectedCodes: Set<CountryCode>,
    onCodesSelected: (Set<CountryCode>) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        title = stringResource(R.string.filter_by_country_code), // Название как в старом коде
        items = CountryCode.COMMON_CODES, // Список стран
        selectedItems = selectedCodes, // Выбранные
        onItemsSelected = onCodesSelected, // Передаем вызов клика
        onDismiss = onDismiss
    ) { countryCode, isSelected ->
        // Отрисовка одной опции
        CountryCodeOption(
            isSelected = isSelected,
            countryCode = countryCode,
            selectedCodes = selectedCodes,
            onCodesSelected = onCodesSelected
        )
    }
}

// Эту функцию НЕ ТРОГАЕМ, оставляем как было, так как это отрисовка одной строки
@Composable
private fun CountryCodeOption(
    isSelected: Boolean,
    countryCode: CountryCode,
    selectedCodes: Set<CountryCode>,
    onCodesSelected: (Set<CountryCode>) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { checked ->
                val newSelection = selectedCodes.toMutableSet()
                if (checked) {
                    newSelection.add(countryCode)
                } else {
                    newSelection.remove(countryCode)
                }
                onCodesSelected(newSelection)
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(countryCode.code)
            Text(
                countryCode.country,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}