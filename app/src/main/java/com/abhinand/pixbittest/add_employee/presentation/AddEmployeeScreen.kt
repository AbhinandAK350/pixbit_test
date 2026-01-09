package com.abhinand.pixbittest.add_employee.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.add_employee.presentation.components.steps.BasicDetailsStep
import com.abhinand.pixbittest.add_employee.presentation.components.steps.ContactDetailsStep
import com.abhinand.pixbittest.add_employee.presentation.components.steps.SalarySchemeStep
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary

enum class AddEmployeeStep {
    BASIC_DETAILS,
    CONTACT_DETAILS,
    SALARY_SCHEME
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployeeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Action) -> Unit,
    viewModel: AddEmployeeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler {
        when (state.currentStep) {
            AddEmployeeStep.CONTACT_DETAILS -> {
                viewModel.onCurrentStepChange(AddEmployeeStep.BASIC_DETAILS)
            }

            AddEmployeeStep.SALARY_SCHEME -> {
                viewModel.onCurrentStepChange(AddEmployeeStep.CONTACT_DETAILS)
            }

            else -> {
                onNavigate(Action.Pop)
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier.padding(start = 10.dp),
                    text = stringResource(R.string.add_employee),
                    fontWeight = FontWeight.W500,
                    color = Primary,
                    fontSize = 20.sp
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Container),
            navigationIcon = {
                IconButton(onClick = {
                    when (state.currentStep) {
                        AddEmployeeStep.CONTACT_DETAILS -> viewModel.onCurrentStepChange(
                            AddEmployeeStep.BASIC_DETAILS
                        )

                        AddEmployeeStep.SALARY_SCHEME -> viewModel.onCurrentStepChange(
                            AddEmployeeStep.CONTACT_DETAILS
                        )

                        else -> onNavigate(Action.Pop)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
            },
        )
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when (state.currentStep) {
                AddEmployeeStep.BASIC_DETAILS -> {
                    BasicDetailsStep(
                        onNext = { viewModel.onCurrentStepChange(AddEmployeeStep.CONTACT_DETAILS) }
                    )
                }

                AddEmployeeStep.CONTACT_DETAILS -> {
                    ContactDetailsStep(
                        onNext = { viewModel.onCurrentStepChange(AddEmployeeStep.SALARY_SCHEME) }
                    )
                }

                AddEmployeeStep.SALARY_SCHEME -> {
                    SalarySchemeStep(
                        onFinish = { /* submit */ }
                    )
                }
            }
        }
    }

}