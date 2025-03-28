package com.maltseva.exchangetracker.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maltseva.exchangetracker.R
import com.maltseva.exchangetracker.presentation.model.CurrencyItem
import com.maltseva.exchangetracker.presentation.viewmodel.AddCurrencyViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCurrencyScreen(
    viewModel: AddCurrencyViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getAllCurrencies()
    }

    val currencies by viewModel.currencyList.collectAsState()

    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                Button(onClick = {
                    viewModel.saveSelected()
                    onBackPressed()
                }) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.done),
                        modifier = Modifier.padding(end = 16.dp),
                    )
                }

            },
            title = { Text(text = stringResource(R.string.add_new_currency)) }
        )

        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(currencies) { item ->
                CurrencyItemView(item, onChecked = {
                    if (it) {
                        viewModel.addSelectedCurrency(item)
                    } else {
                        viewModel.removeSelectedCurrency(item)
                    }
                })
            }
        }
    }

}

@Composable
fun CurrencyItemView(
    item: CurrencyItem,
    onChecked: (Boolean) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = item.currency, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.name, fontSize = 14.sp)
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(checked = item.isSelected, onCheckedChange = onChecked)
    }
}