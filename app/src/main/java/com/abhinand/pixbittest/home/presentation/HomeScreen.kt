package com.abhinand.pixbittest.home.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.navigation.Screen
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.theme.interMedium
import com.abhinand.pixbittest.home.presentation.components.EmployeeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onNavigate: (Action) -> Unit) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = stringResource(R.string.employees),
                fontSize = 20.sp,
                fontFamily = interMedium,
                color = Primary
            )
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Container))
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { onNavigate(Action.Push(Screen.AddEmployee)) },
            shape = CircleShape,
            containerColor = Secondary
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                tint = Color.White
            )
        }
    }) { contentPadding ->

        LazyColumn(
            modifier = modifier.padding(top = contentPadding.calculateTopPadding()),
            contentPadding = PaddingValues(vertical = 15.dp)
        ) {

            items(20) {
                EmployeeItem(employeeImageUrl = "https://picsum.photos/200", onItemClick = {
                    onNavigate(Action.Push(Screen.ProfileDetails("0")))
                })
            }

        }

    }

}