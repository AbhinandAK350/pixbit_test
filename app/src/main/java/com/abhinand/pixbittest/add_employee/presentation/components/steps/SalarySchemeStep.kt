package com.abhinand.pixbittest.add_employee.presentation.components.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.add_employee.presentation.components.StepIndicator
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.theme.interMedium
import com.abhinand.pixbittest.core.theme.interRegular
import com.abhinand.pixbittest.core.theme.interSemiBold
import com.abhinand.pixbittest.profile_details.presentation.SalaryMonthCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PaymentDetail(
    val date: String,
    val amountPercentage: Float,
    val amount: Float = 0f,
    val remarks: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalarySchemeStep(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    date: String,
    onDatePickerClick: () -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    remarks: String,
    onRemarksChange: (String) -> Unit,
    onSaveClick: (Float, Int) -> Unit,
    paymentDetails: List<PaymentDetail>,
    onPaymentDetailsChange: (PaymentDetail) -> Unit,
    onClearPaymentDetails: () -> Unit,
    onDeletePaymentDetail: (Int) -> Unit
) {

    var selectedPeriod by rememberSaveable { mutableStateOf(3) }
    var salary by rememberSaveable { mutableFloatStateOf(30000f) }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

//    var paymentDetails by remember { mutableStateOf<List<PaymentDetail>>(emptyList()) }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var tempSelectedPeriod by remember { mutableStateOf(selectedPeriod) }

    val totalPercentage = paymentDetails.sumOf { it.amountPercentage.toDouble() }.toFloat()
    val remainingPercentage = 100 - totalPercentage
    val remainingAmount = (salary * remainingPercentage) / 100

    if (showConfirmationDialog) {
        AlertDialog(
            containerColor = Container,
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text("Confirm Change", color = Primary) },
            text = { Text("All the added data will be lost. Are you sure you want to continue?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedPeriod = tempSelectedPeriod
                        onClearPaymentDetails()
                        showConfirmationDialog = false
                    }
                ) {
                    Text("Continue", color = Secondary)
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmationDialog = false }) {
                    Text("Cancel", color = Color.DarkGray)
                }
            }
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            dragHandle = {},
            shape = RoundedCornerShape(10.dp),
            containerColor = Color(0xFFFBFDFF)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePickerField("Payment Date", date, onClick = { onDatePickerClick() })

                Spacer(modifier = Modifier.height(16.dp))

                LabeledTextField(
                    label = "Amount %",
                    placeholder = "Enter amount percentage",
                    value = amount,
                    onValueChange = { onAmountChange(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(
                        text = stringResource(R.string.remarks),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1A1A1A)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = remarks,
                        onValueChange = { onRemarksChange(it) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.remarks),
                                fontFamily = interRegular,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp,
                                letterSpacing = 0.sp,
                                color = Color(0xFFB1B1B1)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .border(
                                width = 0.5.dp,
                                color = Secondary,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        singleLine = false,
                        maxLines = 4,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Start,
                            fontFamily = interRegular,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            letterSpacing = 0.sp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Secondary,
                            unfocusedBorderColor = Secondary,
                            focusedLabelColor = Secondary,
                            cursorColor = Secondary,
                            errorBorderColor = Color.Red
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp),
                    onClick = {
                        val newPayment = PaymentDetail(
                            date = date,
                            amount = amount.toFloatOrNull() ?: 0f,
                            amountPercentage = amount.toFloatOrNull() ?: 0f,
                            remarks = remarks
                        )
                        onPaymentDetailsChange(newPayment)
//                        paymentDetails = paymentDetails + newPayment
                        showBottomSheet = false
                        onAmountChange("")
                        onRemarksChange("")
                    },
                    enabled = date.isNotEmpty() && amount.isNotEmpty() && (amount.toFloatOrNull()
                        ?: 0f) <= remainingPercentage && isFutureDate(
                        date,
                        paymentDetails.lastOrNull()?.date
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary)
                ) {
                    Text(text = "Save", fontWeight = FontWeight.W500, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {

        item {
            StepIndicator(step = "Step 3/3", title = "Salary Scheme")

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Contract Period",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ContractChip(
                    text = "3 Months",
                    selected = selectedPeriod == 3,
                    onClick = {
                        if (paymentDetails.isNotEmpty()) {
                            tempSelectedPeriod = 3
                            showConfirmationDialog = true
                        } else {
                            selectedPeriod = 3
                        }
                    }
                )
                ContractChip(
                    text = "6 Months",
                    selected = selectedPeriod == 6,
                    onClick = {
                        if (paymentDetails.isNotEmpty()) {
                            tempSelectedPeriod = 6
                            showConfirmationDialog = true
                        } else {
                            selectedPeriod = 6
                        }
                    }
                )
                ContractChip(
                    text = "12 Months",
                    selected = selectedPeriod == 12,
                    onClick = {
                        if (paymentDetails.isNotEmpty()) {
                            tempSelectedPeriod = 12
                            showConfirmationDialog = true
                        } else {
                            selectedPeriod = 12
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total Salary",
                    fontSize = 14.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight.W600
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFE2F1FF))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "₹ ${salary.toInt()}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = interRegular,
                        letterSpacing = 0.sp,
                        color = Secondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            FlatLineSlider(
                value = salary,
                onValueChange = { salary = it },
                valueRange = 10_000f..200_000f
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "₹ 10,000",
                    color = Color(0xFFB1B1B1),
                    fontSize = 14.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.sp
                )
                Text(
                    text = "₹ 2,00,000",
                    color = Color(0xFFB1B1B1),
                    fontSize = 14.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (paymentDetails.size < selectedPeriod) {
                Button(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        stringResource(
                            R.string.add_monthly_payment_1,
                            selectedPeriod - paymentDetails.size
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        itemsIndexed(paymentDetails) { index, detail ->
            SalaryMonthCard(
                id = index,
                month = "Month ${index + 1}",
                date = detail.date,
                percentage = detail.amountPercentage.toString(),
                amount = (salary * detail.amountPercentage / 100).toInt().toString(),
                remarks = detail.remarks,
                showDeleteButton = true,
                onDeleteClick = {
                    onDeletePaymentDetail(index)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            if (paymentDetails.size == selectedPeriod && totalPercentage == 100f) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp),
                    onClick = { onSaveClick(salary, selectedPeriod) },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                    } else {
                        Text(text = "Save", fontWeight = FontWeight.W500, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Remaining",
                    color = Secondary,
                    fontSize = 14.sp,
                    letterSpacing = 0.sp,
                    fontFamily = interMedium
                )

                Spacer(modifier = Modifier.width(8.dp))

                InfoChip(text = "₹ ${remainingAmount.toInt()}")
                InfoChip(text = "${remainingPercentage}%")
                InfoChip(text = "${selectedPeriod - paymentDetails.size} Month")
            }

            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

fun isFutureDate(dateStr: String, lastDateStr: String?): Boolean {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return try {
        val date = format.parse(dateStr)
        val today = Date()
        if (date != null) {
            if (date.after(today)) {
                if (lastDateStr != null) {
                    val lastDate = format.parse(lastDateStr)
                    date.after(lastDate)
                } else {
                    true
                }
            } else {
                false
            }
        } else {
            false
        }
    } catch (e: Exception) {
        false
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlatLineSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        modifier = modifier.height(32.dp),
        colors = SliderDefaults.colors(
            thumbColor = Secondary,
            activeTrackColor = Secondary,
            inactiveTrackColor = Secondary
        ),
        thumb = {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .background(
                        color = Secondary,
                        shape = CircleShape
                    )
            )
        },
        track = { sliderPositions ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(
                        color = Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(2.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(sliderPositions.coercedValueAsFraction)
                        .height(3.dp)
                        .background(
                            color = Color(0xFF2F80ED),
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
        }
    )
}

@Composable
fun RowScope.ContractChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    weight: Float = 1f
) {
    Box(
        modifier = Modifier
            .weight(weight)
            .height(43.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 0.5.dp,
                color = if (selected) Color(0xFF2979FF) else Color(0xFFBFD9FF),
                shape = RoundedCornerShape(10.dp)
            )
            .background(if (selected) Color(0xFFE9F3FF) else Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = interMedium,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = if (selected) Color(0xFF2979FF) else Color.Black
        )
    }
}

@Composable
fun InfoChip(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .padding(start = 6.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE2F1FF))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color(0xFF2A5277),
            fontWeight = FontWeight.W500,
            letterSpacing = 0.sp,
            fontFamily = interMedium
        )
    }
}
