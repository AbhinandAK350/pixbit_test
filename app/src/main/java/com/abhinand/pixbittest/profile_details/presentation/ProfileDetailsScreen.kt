package com.abhinand.pixbittest.profile_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailsScreen(modifier: Modifier = Modifier, onNavigate: (Action) -> Unit) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier.padding(start = 10.dp),
                    text = stringResource(R.string.profile_details),
                    fontWeight = FontWeight.W500,
                    color = Primary,
                    fontSize = 20.sp
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Container),
            navigationIcon = {
                IconButton(onClick = { onNavigate(Action.Pop) }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
            },
        )
    }) { contentPadding ->

        Column { }

    }
}