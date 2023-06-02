package com.tarekrefaei.littlelemonstore.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.tarekrefaei.littlelemonstore.R
import com.tarekrefaei.littlelemonstore.domain.model.MenuItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainHeader()
        RestaurantCard()
        Categories(state = state, viewModel = viewModel)
        MenuListItems(state = state)
    }
}

@Composable
fun Categories(state: RestaurantState, viewModel: RestaurantViewModel) {
    Column {
        Row {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Orders For Delivery",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Button(
                onClick = {
                    viewModel.onEvent(
                        RestaurantEvents.OnFilter("")
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.primary2)
                ),
            ) {
                Text(text = "Reset")
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            val set = state.menuList.map {
                it.category
            }.toSet()
            items(count = set.size) { index ->
                Button(
                    onClick = {
                        viewModel.onEvent(
                            RestaurantEvents.OnFilter(set.elementAt(index))
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.primary2)
                    ),
                ) {
                    Text(text = set.elementAt(index))
                }
            }
        }
        Divider(
            modifier = Modifier.padding(
                horizontal = 16.dp
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun RestaurantCard() {

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .verticalScroll(rememberScrollState(), reverseScrolling = true)
            .background(
                colorResource(id = R.color.primary1)
            )
            .padding(12.dp)
    ) {
        Text(
            text = "Little Lemon",
            style = TextStyle(
                color = colorResource(id = R.color.primary2),
            ),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )
        Text(
            text = "Chicago",
            style = TextStyle(
                color = colorResource(id = R.color.white),
            ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                style = TextStyle(
                    color = colorResource(id = R.color.white),
                ),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Image(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.white),
                    )
                    .weight(0.3f)
                    .width(150.dp)
                    .height(100.dp),
                painter = painterResource(id = R.drawable.hero_image),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white))
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            delay(50)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            value = "",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            placeholder = {
                Text(text = "Enter Your Search Here ...")
            },
            onValueChange = {},
            label = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        )
    }
}

@Composable
fun MainHeader() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.15f)
                .fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .weight(0.70f)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .weight(0.15f)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RestaurantItem(
    menuList: MenuItems
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(0.7f)
        ) {
            Text(
                text = menuList.title,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Text(
                text = menuList.description,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )
            Text(
                text = "\$ ${menuList.price}",
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )
        }
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp),
            contentAlignment = Center
        ) {
            val painter = rememberImagePainter(
                data = menuList.image,
                builder = {
                    placeholder(R.drawable.logo)
                    crossfade(2000)
                }
            )
            Image(
                painter = painter,
                contentDescription = menuList.description,
            )
        }
    }
}

@Composable
fun MenuListItems(state: RestaurantState) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
    ) {
        items(state.menuList.size) { i ->
            RestaurantItem(state.menuList[i])
            if (i < state.menuList.size) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                )
            }
        }
    }
}