package com.shermanrex.movierex.presention.feature.search.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    onTextFieldChange: (String) -> Unit,
    onClearTextField: () -> Unit,
) {

    var focusManager = LocalFocusManager.current

    var isTextFieldFocused by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .onFocusChanged {
                isTextFieldFocused = it.isFocused
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            modifier = modifier.weight(0.8f),
            value = textFieldValue,
            onValueChange = {
                onTextFieldChange(it)
            },
            trailingIcon = {
                if (textFieldValue.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onClearTextField()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "",
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_name),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            ),
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
        )

        AnimatedVisibility(
            visible = isTextFieldFocused,
        ) {
            TextButton(
                onClick = {
                    focusManager.clearFocus()
                },
            ) {
                Text(
                    text = "Cancel",
                    fontWeight = FontWeight.Bold,
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    InterviewMovieRexTheme {
        SearchTextField(
            textFieldValue = "",
            onTextFieldChange = {},
            onClearTextField = {},
        )
    }
}