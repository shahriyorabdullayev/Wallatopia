package uz.droid.wallatopia

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import uz.droid.wallatopia.common.theme.WallatopiaAppTheme
import uz.droid.wallatopia.common.utils.NoRippleInteractionSource
import uz.droid.wallatopia.presentation.navigation.HomeNavGraph
import uz.droid.wallatopia.presentation.screens.CategoryDetailsScreen
import uz.droid.wallatopia.presentation.screens.SearchScreen
import uz.droid.wallatopia.presentation.screens.SplashScreen


@Composable
@Preview
fun App() {
    KoinContext {
        WallatopiaAppTheme {
            HavHostMain()
//            AutoComplete()
        }
    }
}

@Composable
fun AutoComplete() {

    val animals = listOf(
        "Lion",
        "Tiger",
        "Leopard",
        "Cheetah",
        "Giraffe",
        "Elephant",
        "Zebra",
        "Kangaroo",
        "Koala",
        "Panda",
        "Gorilla",
        "Hippopotamus",
        "Rhinoceros",
        "Orangutan",
        "Polar Bear",
        "Grizzly Bear",
        "Sloth",
        "Kangaroo",
        "Koala",
        "Panda",
        "Gorilla",
        "Hippopotamus",
        "Rhinoceros",
        "Orangutan",
        "Polar Bear",
        "Grizzly Bear",
        "Sloth",
        "Kangaroo",
        "Koala",
        "Panda",
        "Gorilla",
        "Hippopotamus",
        "Rhinoceros",
        "Orangutan",
        "Polar Bear",
        "Grizzly Bear",
        "Sloth",
        "Kangaroo",
        "Koala",
        "Panda",
        "Gorilla",
        "Hippopotamus",
        "Rhinoceros",
        "Orangutan",
        "Polar Bear",
        "Grizzly Bear",
        "Sloth"
    )


    var category by remember {
        mutableStateOf("")
    }

    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    // Category Field
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {

        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
            text = "Animals",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )

        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
                        .border(
                            width = 1.8.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = category,
                    onValueChange = {
                        category = it
                        expanded = true
                    },
                    placeholder = { Text("Enter any Animals Name") },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }

            if (expanded) {
                Popup(
                    alignment = Alignment.TopCenter,
                    onDismissRequest = { },
                    offset = IntOffset(x= 0, y = 120),
                    content = {
                    Card(
                        modifier = Modifier
                            .background(color = Color.White)
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth()
                        ,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 150.dp),
                        ) {

                            if (category.isNotEmpty()) {
                                items(
                                    animals.filter {
                                        it.lowercase()
                                            .contains(category.lowercase()) || it.lowercase()
                                            .contains("others")
                                    }
                                        .sorted()
                                ) {
                                    ItemsCategory(title = it) { title ->
                                        category = title
                                        expanded = false
                                    }
                                }
                            } else {
                                items(
                                    animals.sorted()
                                ) {
                                    ItemsCategory(title = it) { title ->
                                        category = title
                                        expanded = false
                                    }
                                }
                            }

                        }

                    }
                    }
                )
            }


            Text(
                modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
                text = "Animals",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )

        }

    }


}

@Composable
fun ItemsCategory(
    title: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}

@Composable
fun HavHostMain(navController: NavHostController = rememberNavController()) {
     NavHost(navController = navController, startDestination = Screens.SplashScreen) {
        composable<Screens.SplashScreen> {
            SplashScreen(
                navigateToHome = {
                    navController.navigate(Screens.HomeGraph) {
                        popUpTo(Screens.SplashScreen) {
                             inclusive = true
                        }
                    }
                }
            )
        }
        composable<Screens.HomeGraph> {
            HomeNavGraph(
                globalNavController = navController
            )
        }
         composable<Screens.CategoryDetailsScreen> { backStackEntry ->
             val category: Screens.CategoryDetailsScreen = backStackEntry.toRoute()
             CategoryDetailsScreen(
                 categoryId = category.categoryId,
                 onBackPressed = navController::popBackStack
             )
         }
         composable<Screens.SearchScreen> {
             SearchScreen(
                 onBackPressed = navController::popBackStack,
                 navigateToCategoryDetails = {
                     navController.navigate(Screens.CategoryDetailsScreen(it))
                 }
             )
         }
    }
}
