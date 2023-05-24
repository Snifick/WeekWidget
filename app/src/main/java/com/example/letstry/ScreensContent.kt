package com.example.letstry


import android.content.Intent


import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letstry.BottomNavigation.clrDandM
import com.example.letstry.ui.theme.*
import java.util.*


@Composable
fun ContentScreen1() {

    Image(
        painter = painterResource(id = R.drawable.main1),
        contentDescription = "back1",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        BottomViewScreen1()
    }
}


@Composable
fun ContentScreen2() {
    val weekVM: WeekViewModel = viewModel()
    Image(
        painter = painterResource(id = R.drawable.main2),
        contentDescription = "back2",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(bottom = 74.dp)
    ) {
        TopViewScreen2(weekVM)
        BottomViewSetting()
        BottomViewCustomization(weekVM)
    }
}



@Composable
fun BottomViewScreen1() {


    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.TopCenter
    )
    {
        Card(
            shape = RoundedCornerShape(12.dp), modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            backgroundColor = clrDandM(clrNight = clrBotNight, clrLight = Teal200)
        ) {

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .wrapContentSize()
            ) {
                Text(
                    text = stringResource(id = R.string.mainWidgetString),
                    style = TextStyle(fontSize = 26.sp),
                    modifier = Modifier
                        .fillMaxWidth(), color = clrDandM(clrNight = Color.White, clrLight =Color.Black )
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(vertical = 8.dp), color = clrDandM(clrNight = Color.Gray, clrLight =Color.Gray )
                )
                Text(
                    text = stringResource(id = R.string.StringUser),
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth(), color = clrDandM(clrNight = Color.White, clrLight =Color.Black )
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(vertical = 8.dp), color = clrDandM(clrNight = Color.Gray, clrLight =Color.Gray )
                )
                Text(
                    text = stringResource(id = R.string.customWidget),
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth(), color = clrDandM(clrNight = Color.White, clrLight =Color.Black )
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(vertical = 8.dp), color = clrDandM(clrNight = Color.Gray, clrLight =Color.Gray)
                )
                Text(
                    text = "Виджет обновляется автоматически каждые 30 минут и по нажатию на него.\nПри ручной настройке виджет все так же продолжит обновляться автоматически.",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth(), color = clrDandM(clrNight = Color.White, clrLight =Color.Black )
                )
            }


        }

    }
}


@Composable
fun TopViewScreen2(weekVM: WeekViewModel) {

    val context = LocalContext.current
    val preferences = remember { MyPref(context) }
    weekVM.backId.value.toString()

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(1f)
            .fillMaxHeight(0.15f), shape = RoundedCornerShape(24.dp), elevation = 8.dp
    ) {
        Box(
            modifier = Modifier

                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(preferences.clrBegin!!), Color(preferences.clrEnd!!))
                    )
                )
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = "WeekWidget",
                    style = TextStyle(fontSize = 30.sp, color = clrDandM(clrNight =Color.Black, clrLight = Color.Black)),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "in search of convenience",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = clrDandM(clrNight = Color.Black,  clrLight = Color.Black),
                        fontFamily = FontFamily.Cursive
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }


}

@Composable
fun BottomViewSetting() {
    val context = LocalContext.current
    val preferences = remember(context) { MyPref(context) }
    val test = remember { mutableStateOf(preferences.inOrder.toString()) }
    val isAllowed = remember { mutableStateOf(false) }
    val selectedButton = ButtonDefaults.buttonColors(
        backgroundColor = selectedButtoncolorBack,
        contentColor = selectedButtoncolorPrimary,
        disabledBackgroundColor = Color.LightGray,
        disabledContentColor = Color.Gray
    )
    val unSelectedButton = ButtonDefaults.buttonColors(
        backgroundColor = Color.LightGray,
        contentColor = Color.Gray,
        disabledBackgroundColor = Color.LightGray,
        disabledContentColor = Color.Gray
    )
    val widget: Widget = Widget()
    val calendar = Calendar.getInstance()
    var isWeekTop by rememberSaveable { mutableStateOf(preferences.weekPref == "Верхняя") }
    var isWeekBot by rememberSaveable { mutableStateOf(preferences.weekPref == "Нижняя") }
    val txtClr = when (isAllowed.value) {
        true -> Color.Black
        else -> Color.Gray
    }




    Card(
        shape = RoundedCornerShape(12.dp), modifier = Modifier
            .padding(16.dp)
            .wrapContentSize()
    ) {

        Column(
            modifier = Modifier
                .background(color = clrDandM(clrNight = black, clrLight = Teal200))
                .padding(16.dp)
                .wrapContentSize()
                .animateContentSize()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.self_change),
                    style = TextStyle(fontSize = 22.sp,color = clrDandM(clrNight = clrWhite, clrLight = black))
                )
                Checkbox(
                    checked = isAllowed.value,
                    onCheckedChange = { isAllowed.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(
                            0xFF1F8623
                        ), uncheckedColor = Color.Gray
                    )
                )
            }

            if (isAllowed.value) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),     color  = clrDandM(clrNight = cdividerNight, clrLight = cdivider)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(

                        value = test.value,
                        onValueChange = {
                            test.value = it
                        },

                        enabled = isAllowed.value,
                        textStyle = TextStyle(
                            fontSize = (16.sp),
                            color = clrDandM(clrNight = clrWhite, clrLight = black)
                        ),
                        isError = (!test.value.isDigitsOnly() || test.value.isBlank()|| test.value.length > 5),
                        modifier = Modifier.weight(0.65f),
                        label = { Text(text = "Текущая неделя по счету:") },
                        colors = TextFieldDefaults.textFieldColors(

                            focusedLabelColor = clrDandM(clrNight = clrWhite, clrLight = black),
                            unfocusedIndicatorColor = clrDandM(clrNight = clrWhite, clrLight = black),
                            backgroundColor =clrDandM(clrNight = Color.DarkGray, clrLight = Color.White),
                           textColor = clrDandM(clrNight = clrWhite, clrLight = black),
                            cursorColor = clrDandM(clrNight = clrWhite, clrLight = black),
                            unfocusedLabelColor = clrDandM(clrNight = clrWhite, clrLight = black),
                            focusedIndicatorColor = clrDandM(clrNight = clrWhite, clrLight = black),
                            errorLabelColor =clrDandM(clrNight = Color.Red, clrLight = Color.Red),
                            errorIndicatorColor = clrDandM(clrNight = Color.Red, clrLight = Color.Red),

                        )
                    )
                    Spacer(modifier = Modifier.weight(0.1f))
                    OutlinedButton(
                        onClick = {
                            if (!test.value.isDigitsOnly() || test.value.isBlank() || test.value.length > 5) {
                                Toast.makeText(context, "Неверный ввод недели", Toast.LENGTH_SHORT)
                                    .show()
                            } else {


                                isAllowed.value = false
                                preferences.inOrder = test.value.toInt()
                                preferences.inOrderWeekOfYearBegin =
                                    calendar.get(Calendar.WEEK_OF_YEAR)
                                preferences.inOrderStartFrom = preferences.inOrder
                                preferences.oddWeek = preferences.weekPref
                                Toast.makeText(
                                    context,
                                    "Изменения сохранены",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                widget.onReceive(context, Intent("WidgetTextClicked"))

                            }
                        },

                        modifier = Modifier.weight(0.25f),
                        shape = RoundedCornerShape(16.dp),
                        colors = selectedButton,
                    ) {
                        Text(text = "Ок", maxLines = 1)
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(vertical = 8.dp),     color  = clrDandM(clrNight = cdividerNight, clrLight = cdivider)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedButton(
                        onClick = {


                            preferences.oddWeek = "Верхняя"
                            preferences.inOrderStartFrom = preferences.inOrder
                            isWeekTop = true
                            isWeekBot = false
                            preferences.weekPref = "Верхняя"
                            widget.onReceive(context, Intent("WidgetTextClicked"))

                        },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(16.dp),
                        colors = if (isWeekTop) {
                            selectedButton
                        } else {
                            unSelectedButton
                        }
                    ) {
                        Text(text = "Верхняя")
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    OutlinedButton(
                        onClick = {

                            preferences.oddWeek = "Нижняя"
                            preferences.inOrderStartFrom = preferences.inOrder
                            preferences.weekPref = "Нижняя"
                            isWeekTop = false
                            isWeekBot = true
                            widget.onReceive(context, Intent("WidgetTextClicked"))

                        },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(16.dp),
                        colors = if (isWeekBot) {
                            selectedButton
                        } else {
                            unSelectedButton
                        }
                    ) {
                        Text(text = "Нижняя")
                    }

                }
            }
        }

    }


}

@Composable
fun BottomViewCustomization(weekVM: WeekViewModel) {
    val isAllowed = remember { mutableStateOf(false) }

    val icon = remember { mutableStateOf(R.drawable.baseline_keyboard_arrow_down_24) }
    Card(
        shape = RoundedCornerShape(12.dp), modifier = Modifier
            .padding(horizontal = 16.dp)
            .wrapContentSize()
    ) {

        Column(
            modifier = Modifier
                .background(color = clrDandM(clrNight = black, clrLight = Teal200))
                .padding(16.dp)
                .wrapContentSize()
                .animateContentSize()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.customV),
                    style = TextStyle(fontSize = 22.sp, color = clrDandM(clrNight = clrWhite, clrLight = black))

                )
                IconButton(onClick = {
                    isAllowed.value = !isAllowed.value

                    if (isAllowed.value)
                        icon.value = R.drawable.baseline_keyboard_arrow_up_24
                    else
                        icon.value = R.drawable.baseline_keyboard_arrow_down_24

                }) {
                    Icon(painter = painterResource(id = icon.value), contentDescription = "down", tint = clrDandM(clrNight = clrWhite, clrLight = black))
                }
            }

            if (isAllowed.value) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BackMutable(clr1 = Color(0xFFFBD33E), clr2 = Color(0xFFF7893B), clr3 =Color(0xFFF16164) , clr4 =Color(0xFFF16164) , idshka =  R.drawable.app_widget_background3 , weekVM =weekVM )
                    BackView(
                        Color(0xFFFBD33E),
                        Color(0xFFF7893B),
                        R.drawable.app_widget_background1,
                        weekVM
                    )
                    BackView(
                        Color(0xFFF7893B),
                        Color(0xFFF16164),
                        R.drawable.app_widget_background2,
                        weekVM
                    )
                    BackView(
                        Color(0xFFF16164),
                        Color(0xFFC04992),
                        R.drawable.app_widget_background3,
                        weekVM
                    )


                    BackView(
                        Color(0xFF6E7ED6),
                        Color(0xFFC04992),
                        R.drawable.app_widget_background6,
                        weekVM
                    )
                    BackView(
                        Color(0xFFCDDC39),
                        Color(0xFF4CAF50),
                        R.drawable.app_widget_background4,
                        weekVM
                    )
                    BackView(
                        Color(0xFF4CAF50),
                        Color(0xFFFFC107),
                        R.drawable.app_widget_background8,
                        weekVM
                    )
                    BackView(    Color(0xFFFFEB3B),
                        Color(0xFFFF5722)
                    ,
                        R.drawable.app_widget_background9,
                        weekVM
                    )
                    BackView(
                        Color(0xFFFFFFFF),
                        Color(0xFFFF0057),
                        R.drawable.app_widget_background7,
                        weekVM
                    )
                    BackView(
                        Color(0xFFFFFFFF),
                        Color(0xFF00BCD4),
                        R.drawable.app_widget_background5,
                        weekVM
                    )


                }

            }
        }
    }
}

@Composable
fun BackView(clrB: Color, clrE: Color, idshka: Int, weekVM: WeekViewModel) {


    val context = LocalContext.current
    val preferences = remember(context) { MyPref(context) }
    val widget: Widget = Widget()

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(height = 100.dp, width = 330.dp)
            .clickable {


                preferences.clrBegin = clrB.toArgb()
                preferences.clrEnd = clrE.toArgb()
                preferences.backID = idshka
                weekVM.backId.value = !weekVM.backId.value
                preferences.mutableBackIsSelected = false
                widget.onReceive(context, Intent("WidgetTextClicked"))
            }, shape = RoundedCornerShape(24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(colors = listOf(clrB, clrE))
                )
        )
        {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()

            ) {

                Text(
                    text = "WeekWidget",  modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(fontSize = 30.sp, color = clrDandM(clrNight = black, clrLight = black)),
                    textAlign = TextAlign.End

                )
                Text(
                    text = "in search of convenience",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = clrDandM(clrNight = black, clrLight = black),
                        fontFamily = FontFamily.Cursive
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }


}

@Composable
fun BackMutable(clr1: Color, clr2: Color,clr3: Color,clr4: Color, idshka: Int, weekVM: WeekViewModel) {


    val context = LocalContext.current
    val preferences = remember(context) { MyPref(context) }
    val widget: Widget = Widget()

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(height = 100.dp, width = 330.dp)
            .clickable {

                preferences.clrBegin = clr1.toArgb()
                preferences.clrEnd = clr4.toArgb()
                preferences.backID = idshka
                weekVM.backId.value = !weekVM.backId.value
                preferences.mutableBackIsSelected = true
                widget.onReceive(context, Intent("WidgetTextClicked"))
            }, shape = RoundedCornerShape(24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            clr1,
                            clr2,
                            clr3,
                            clr4,
                            clr3,
                            clr2,
                            clr1
                        )
                    )
                )
        )
        {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()

            ) {

                Text(
                    text = "Динамический WeekWidget",
                    style = TextStyle(fontSize = 20.sp, color = clrDandM(clrNight = black, clrLight = black)),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "in search of convenience",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = clrDandM(clrNight = black, clrLight = black),
                        fontFamily = FontFamily.Cursive
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }


}

