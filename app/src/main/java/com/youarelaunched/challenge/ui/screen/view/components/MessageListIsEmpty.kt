package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun MessageListIsEmpty(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                28.dp, 0.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = VendorAppTheme.colors.textTitle,
            style = VendorAppTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
            text = description,
            color = VendorAppTheme.colors.textDescription,
            style = VendorAppTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}