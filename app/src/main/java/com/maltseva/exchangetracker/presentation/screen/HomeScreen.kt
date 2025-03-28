package com.maltseva.exchangetracker.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maltseva.exchangetracker.R
import com.maltseva.exchangetracker.presentation.model.SavedCurrencyItem
import com.maltseva.exchangetracker.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    onAddCurrencyClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        homeViewModel.getSavedCurrencyList()
        homeViewModel.startUpdates()
    }

    val currencyList by homeViewModel.usersCurrencyList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (currencyList.isEmpty()) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.no_items),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(vertical = 100.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 80.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(currencyList) { item ->
                    CurrencyItem(item, onDeleteClick = {
                        homeViewModel.deleteCurrencyItem(item)
                    })
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onAddCurrencyClick,
        ) {
            Text(text = stringResource(R.string.add_new_currency))
        }
    }
}

@Composable
fun CurrencyItem(
    item: SavedCurrencyItem,
    onDeleteClick: () -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { newValue ->
            if (newValue == SwipeToDismissBoxValue.EndToStart) {
                onDeleteClick
                true
            } else {
                false
            }
        }
    )
    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Button(
                onClick = onDeleteClick,
                modifier = Modifier
            ) { Text(text = stringResource(R.string.delete)) }
        }
    ) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(bottom = 16.dp)
                .background(Color.White)
        ) {
            Column {
                Text(text = item.currency)
                Text(text = item.name)
            }
            Spacer(modifier = Modifier.weight(1f))

            Text(text = item.rate.toString())
        }
    }
}