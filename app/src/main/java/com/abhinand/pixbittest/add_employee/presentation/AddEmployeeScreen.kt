package com.abhinand.pixbittest.add_employee.presentation

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.add_employee.presentation.components.steps.BasicDetailsStep
import com.abhinand.pixbittest.add_employee.presentation.components.steps.ContactDetailsStep
import com.abhinand.pixbittest.add_employee.presentation.components.steps.SalarySchemeStep
import com.abhinand.pixbittest.add_employee.presentation.components.steps.toUri
import com.abhinand.pixbittest.core.components.ErrorAlert
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.utils.Util.toMillisOrNull
import java.io.File
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

enum class AddEmployeeStep {
    BASIC_DETAILS,
    CONTACT_DETAILS,
    SALARY_SCHEME
}

enum class DatePickerTarget {
    DOB,
    PAYMENT_DATE
}

fun Uri.toFile(context: Context): File {
    val resolver = context.contentResolver
    val fileName = queryFileName(resolver) ?: "temp_file"
    val tempFile = File(context.cacheDir, fileName)

    resolver.openInputStream(this)?.use { input ->
        tempFile.outputStream().use { output ->
            input.copyTo(output)
        }
    } ?: throw IllegalArgumentException("Unable to open URI input stream")

    return tempFile
}

private fun Uri.queryFileName(resolver: ContentResolver): String? {
    return resolver.query(this, null, null, null, null)?.use { cursor ->
        val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (index != -1 && cursor.moveToFirst()) cursor.getString(index) else null
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployeeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Action) -> Unit,
    viewModel: AddEmployeeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (state.errorMessage != null) {
        ErrorAlert(state.errorMessage ?: "Unknown error") { viewModel.dismissErrorDialog() }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.onProfileImageChange(uri?.toFile(context))
    }

    val resumePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.onResumeFileChange(uri?.toFile(context))
    }

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

    val initialMillis = when (state.datePickerTarget) {
        DatePickerTarget.DOB ->
            state.dob.toMillisOrNull()

        DatePickerTarget.PAYMENT_DATE ->
            state.paymentDate.toMillisOrNull()

        else -> null
    }

    if (state.showDatePicker && state.datePickerTarget != null) {

        val datePickerState = rememberDatePickerState(initialMillis)

        DatePickerDialog(
            colors = DatePickerDefaults.colors(
                containerColor = Container
            ),
            onDismissRequest = { viewModel.closeDatePicker() },
            properties = DialogProperties(
                usePlatformDefaultWidth = true
            ),
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            val date = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .format(formatter)

                            when (state.datePickerTarget) {
                                DatePickerTarget.DOB ->
                                    viewModel.onDobChange(date)

                                DatePickerTarget.PAYMENT_DATE ->
                                    viewModel.onPaymentDateChange(date)

                                else -> {}
                            }
                        }
                        viewModel.closeDatePicker()
                    }
                ) {
                    Text("OK", color = Secondary)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.closeDatePicker() }
                ) {
                    Text("Cancel", color = Secondary)
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = Container,
                    todayContentColor = Primary,
                    todayDateBorderColor = Primary,
                    selectedDayContainerColor = Primary
                )
            )
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
    }, containerColor = Color(0xFFFBFDFF)) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when (state.currentStep) {
                AddEmployeeStep.BASIC_DETAILS -> {
                    BasicDetailsStep(
                        imageUri = state.profileImage,
                        onImageClick = { imagePickerLauncher.launch("image/*") },
                        onNext = { viewModel.onCurrentStepChange(AddEmployeeStep.CONTACT_DETAILS) },
                        firstName = state.firstName,
                        onFirstNameChange = viewModel::onFirstNameChange,
                        lastName = state.lastName,
                        onLastNameChange = viewModel::onLastNameChange,
                        dob = state.dob,
                        onDatePickerClick = { viewModel.onShowDatePickerChange(DatePickerTarget.DOB) },
                        gender = state.gender,
                        onGenderChange = viewModel::onGenderChange,
                        isGenderDropdownOpen = state.isGenderDropdownOpen,
                        onGenderDropdownOpenChange = viewModel::onGenderDropdownOpenChange,
                        genderOptions = state.genderOptions,
                        designation = state.designation,
                        onDesignationChange = viewModel::onDesignationChange,
                        isDesignationDropdownOpen = state.isDesignationDropdownOpen,
                        onDesignationDropdownOpenChange = viewModel::onDesignationDropdownOpenChange,
                        designationOptions = state.designationOptions,
                        isNextButtonEnabled = state.isNextButtonEnabled,
                        resumeFile = state.resumeFile,
                        resumeFileName = state.resumeFileName,
                        onResumeClick = { resumePickerLauncher.launch("application/pdf") },
                        onViewResumeClick = {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.setDataAndType(
                                state.resumeFile?.toUri(context),
                                "application/pdf"
                            )
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            context.startActivity(intent)
                        }
                    )
                }

                AddEmployeeStep.CONTACT_DETAILS -> {
                    ContactDetailsStep(
                        mobileNumber = state.mobileNumber,
                        onMobileNumberChange = viewModel::onMobileNumberChange,
                        email = state.email,
                        onEmailChange = viewModel::onEmailChange,
                        address = state.address,
                        onAddressChange = viewModel::onAddressChange,
                        onNext = { viewModel.onCurrentStepChange(AddEmployeeStep.SALARY_SCHEME) },
                        emailTouched = state.emailTouched,
                        isEmailValid = state.isEmailValid,
                        isContactNextButtonEnabled = state.isContactNextButtonEnabled
                    )
                }

                AddEmployeeStep.SALARY_SCHEME -> {
                    SalarySchemeStep(
                        isLoading = state.isLoading,
                        date = state.paymentDate,
                        onDatePickerClick = { viewModel.onShowDatePickerChange(DatePickerTarget.PAYMENT_DATE) },
                        amount = state.amount,
                        onAmountChange = viewModel::onAmountChange,
                        remarks = state.remarks,
                        onRemarksChange = viewModel::onRemarksChange,
                        onSaveClick = viewModel::onSaveClick,
                        paymentDetails = state.paymentDetails,
                        onPaymentDetailsChange = viewModel::onPaymentDetailsChange,
                        onClearPaymentDetails = viewModel::onClearPaymentDetails,
                        onDeletePaymentDetail = viewModel::onDeletePaymentDetail,
                        onNavigate = onNavigate
                    )
                }
            }
        }
    }

}
