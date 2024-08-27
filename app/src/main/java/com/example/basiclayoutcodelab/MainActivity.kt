package com.example.basiclayoutcodelab

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutcodelab.ui.theme.BasicLayoutCodelabTheme

class MainActivity : ComponentActivity() {
    /**
     * Since calculateWindowSize() is still experimental you will need to opt into the
     * ExperimentalMaterial3WindowSizeClassApi class.
    */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            BasicLayoutApp(windowSizeClass)
//            BasicLayoutAppLandscape()
        }
    }
}

@Composable
fun AppBar(modifier: Modifier = Modifier) {
    SearchBarCard(modifier)
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    BasicLayoutCodelabTheme {
        AppBar()
    }
}

@Composable
fun SearchBarCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .wrapContentSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                SearchBar(name = "search")
            }
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(id = R.string.account),
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .widthIn(min = 56.dp)
                    .padding(all = 12.dp)
            )
        }
    }
}

@Composable
fun SearchBar(name: String, modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder_search))
        },
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AlignYourBodyElementPreview() {
    BasicLayoutCodelabTheme {
        AlignYourBodyElement(
            drawable = R.drawable.demo_chicken_sandwich,
            text = R.string.sandwich,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(all = 16.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicLayoutCodelabTheme {
        FavoriteCollectionCard(
            drawable = R.drawable.demo_chicken_sandwich,
            text = R.string.sandwich,
            modifier = Modifier.padding(8.dp)
        )
    }
}

val alignYourBodyData: List<AlignBody> = listOf(
    AlignBody(R.drawable.demo_chicken_sandwich, R.string.sandwich),
    AlignBody(R.drawable.demo_meat_marshmallo_pizza, R.string.pizza),
    AlignBody(R.drawable.demo_meat_marshmallo_pizza, R.string.mohito),
    AlignBody(R.drawable.demo_meat_marshmallo_pizza, R.string.melon),
    AlignBody(R.drawable.demo_meat_marshmallo_pizza, R.string.ice_late)
)

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(drawable = item.drawableRes, text = item.text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    BasicLayoutCodelabTheme {
        AlignYourBodyRow()
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(alignYourBodyData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawableRes,
                text = item.text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionsGridPreview() {
    BasicLayoutCodelabTheme {
        FavoriteCollectionsGrid(
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AppBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collection) {
            FavoriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true,
    heightDp = 680)
@Composable
fun HomeScreenPreview() {
    BasicLayoutCodelabTheme {
        HomeScreen()
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBar(modifier = modifier) {
            NavigationBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    ) },
                label = {
                    Text(stringResource(id = R.string.bottom_navigation_home)) },
            )
            NavigationBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    ) },
                label = { Text(text = stringResource(id = R.string.bottom_navigation_profile)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview(){
    BasicLayoutCodelabTheme {
        BottomNavigation()
    }
}

@Composable
fun BasicLayoutAppPortrait(){
    BasicLayoutCodelabTheme {
        Scaffold (
            bottomBar = { BottomNavigation() }
        ) { paddingValues ->
            HomeScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasicLayoutAppPortraitPreview(){
    BasicLayoutAppPortrait()
}

@Composable
fun BasicNavigationRail(modifier: Modifier = Modifier){
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    )
    {
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            NavigationRailItem(
                selected = true,
                label = {
                        Text(text = stringResource(id = R.string.bottom_navigation_home))
                },
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_profile))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationRailPreview(){
    BasicNavigationRail()
}

@Composable
fun BasicLayoutAppLandscape(){
    BasicLayoutCodelabTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                BasicNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasicLayoutAppLandscapePreview() {
    BasicLayoutAppLandscape()
}

@Composable
fun BasicLayoutApp(windowSizeClass: WindowSizeClass){
    //There are three window size class widths: Compact, Medium and Expanded.
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            BasicLayoutAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            BasicLayoutAppLandscape()
        }
        WindowWidthSizeClass.Medium -> {
            BasicLayoutAppLandscape()
        }
    }
}
