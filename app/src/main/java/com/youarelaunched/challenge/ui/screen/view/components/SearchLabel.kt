package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun SearchLabel(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var value by remember {
        mutableStateOf("")
    }

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        textStyle = TextStyle(
            fontSize = VendorAppTheme.typography.body1.fontSize,
            fontWeight = VendorAppTheme.typography.body1.fontWeight,
            color = VendorAppTheme.colors.textSub
        ),
        decorationBox = { innerTextField ->
            val shape =  RoundedCornerShape(16.dp)
            Box(
                modifier = modifier
                    .height(40.dp)
                    .shadow(
                        elevation = 14.dp,
                        shape = shape
                    )
                    .background(color = Color.White, shape = shape)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(14.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = VendorAppTheme.colors.textSub,
                            style = VendorAppTheme.typography.body1,
                        )
                    } else {
                        innerTextField()
                    }
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = "Search Icon"
                    )
                }
            }
        }
    )
}