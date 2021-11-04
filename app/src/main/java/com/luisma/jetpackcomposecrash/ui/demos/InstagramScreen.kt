package com.luisma.jetpackcomposecrash.ui.demos

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luisma.jetpackcomposecrash.R

@Composable
fun InstagramScreen() {
    val scrollMainColumn = rememberScrollState()

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    fun onTapSelected(idx: Int) {
        selectedTabIndex = idx
    }
    Scaffold(
        topBar = {
            TopBar(
                title = "kermit_the_frog_official_account",
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollMainColumn)
        ) {
            Profile(
                avatar = R.drawable.kermit,
                posts = "600",
                following = "72",
                followers = "99.8 K",
            )
            ProfileDescription(
                shortDescription = "Actor, singer and banjo player",
                description = "Muppet character created and originally performed by J" +
                        "im Henson. Introduced in 1955, " +
                        "I served as the straight " +
                        "man protagonist of numerous " +
                        "Muppet productions, most notably Sesame Street and " +
                        "The Muppet Show, " +
                        "as well as in other " +
                        "television series, " +
                        "feature films, specials, " +
                        "and public service announcements " +
                        "through the years.",
                followersMetions = listOf("some_dude", "some_brat"),
                restFollowers = "17"
            )
            ActionButtons()
            SocialMediaSections()
            TabBarSections(
                onTapSelected = ::onTapSelected,
                selectedTab = selectedTabIndex,
            )

            when (selectedTabIndex) {
                0 -> SectionSelected("Profile")
                1 -> SectionSelected(title = "Places")
                2 -> SectionSelected(title = "Shop")
                3 -> SectionSelected(title = "Favorites")
            }
        }
    }
}

//Sections
@Composable
private fun TopBar(
    title: String = "",
    onTapNotifications: () -> Unit = {},
    onTapMenuMore: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopBarIcon(
            icon = Icons.Filled.ArrowBack,
            contentDescription = "back",
        )
        AllWidthReducedBy(reduce = 132.dp) {
            Text(
                text = title,
                maxLines = 1,
                style = TextStyle(fontWeight = FontWeight.Bold),
                overflow = TextOverflow.Ellipsis,
                fontSize = 24.sp,
            )
        }
        TopBarIcon(
            icon = Icons.Outlined.Notifications,
            contentDescription = "Notifications",
            onTap = onTapNotifications
        )
        TopBarIcon(
            icon = Icons.Outlined.MoreVert,
            contentDescription = "Notifications",
            onTap = onTapMenuMore
        )
    }
}

@Composable
private fun Profile(
    avatar: Int,
    posts: String,
    followers: String,
    following: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            painter = painterResource(id = avatar),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(fraction = .3F)
                .aspectRatio(
                    ratio = 1f,
                    matchHeightConstraintsFirst = true
                )
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .padding(3.dp)
                .clip(shape = CircleShape)
                .background(color = Color.Gray)
        )
        Reaction(
            text = posts,
            description = "Posts",
            modifier = Modifier.weight(23F)
        )
        Reaction(
            text = followers,
            description = "Followers",
            modifier = Modifier.weight(23F)
        )
        Reaction(
            text = following,
            description = "Following",
            modifier = Modifier.weight(23F)
        )
    }
}

@Composable
private fun ProfileDescription(
    shortDescription: String,
    description: String,
    followersMetions: List<String> = listOf(),
    restFollowers: String,
) {
    var allText by remember {
        mutableStateOf(false)
    }

    fun showAll() {
        allText = !allText
    }

    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        //Principal description
        Text(
            text = shortDescription,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        //Secondary description
        Text(
            text = description,
            maxLines = if (allText) Int.MAX_VALUE else 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.clickable {
                showAll()
            }
        )
        //Followed description
        Text(text = buildAnnotatedString {
            val boldStyle = SpanStyle(
                fontWeight = FontWeight.Bold
            )
            append("Followed by ")
            followersMetions.forEachIndexed { index, it ->
                pushStyle(boldStyle)
                append(it)
                pop()
                if (index == followersMetions.lastIndex) {
                    append(" and ")
                } else {
                    append(", ")
                }
            }
            if (restFollowers.isNotEmpty()) {
                pushStyle(boldStyle)
                append("$restFollowers other(s)")
            }
        })
    }


}

@Composable
private fun ActionButtons() {
    val scroll = rememberScrollState()

    Row(
        modifier = Modifier
            .padding(
                top = 20.dp,
                bottom = 20.dp,
                start = 10.dp
            )
            .horizontalScroll(
                state = scroll,
            ),
    ) {
        ActionButton(
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown
        )
        ActionButton(
            text = "Message",
        )
        ActionButton(
            text = "Email",
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown
        )
    }
}

@Composable
private fun SocialMediaSections() {
    val scrollState = rememberScrollState()
    Row(
        Modifier
            .horizontalScroll(
                scrollState,
            )
            .padding(start = 10.dp, bottom = 10.dp)
    ) {
        SocialMediaSection(
            icon = Icons.Rounded.Face,
            text = "SocialMedia1"
        )
        SocialMediaSection(
            icon = Icons.Rounded.Star,
            text = "SocialMedia2"
        )
        SocialMediaSection(
            icon = Icons.Rounded.ThumbUp,
            text = "SocialMedia3"
        )
        SocialMediaSection(
            icon = Icons.Rounded.ShoppingCart,
            text = "SocialMedia4"
        )
    }
}

@Composable
private fun TabBarSections(
    selectedTab: Int,
    onTapSelected: (idx: Int) -> Unit,
) {


    TabRow(
        selectedTabIndex = selectedTab,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        divider = {
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
            )
        },
    ) {
        TabSelection(
            idx = 0,
            selectedIdx = selectedTab,
            onTap = onTapSelected,
            icon = Icons.Default.AccountCircle
        )
        TabSelection(
            idx = 1,
            selectedIdx = selectedTab,
            onTap = onTapSelected,
            icon = Icons.Default.Place
        )
        TabSelection(
            idx = 2,
            selectedIdx = selectedTab,
            onTap = onTapSelected,
            icon = Icons.Default.ShoppingCart
        )
        TabSelection(
            idx = 3,
            selectedIdx = selectedTab,
            onTap = onTapSelected,
            icon = Icons.Default.Favorite
        )
    }
}


//Reusable
@Composable
private fun SectionSelected(title: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            title,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
private fun TabSelection(
    idx: Int,
    selectedIdx: Int,
    onTap: (idx: Int) -> Unit,
    icon: ImageVector,
) {
    val selected = selectedIdx == idx
    Tab(
        selected = selected,
        onClick = {
            onTap(idx)
        },
        selectedContentColor = Color.Black,
        unselectedContentColor = Color.LightGray,
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
        )
    }
}


@Composable
private fun Reaction(
    text: String = "",
    description: String = "",
    modifier: Modifier,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = description,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }

}


@Composable
private fun AllWidthReducedBy(
    reduce: Dp = 0.dp,
    composable: @Composable () -> Unit = {}
) {
    val w =
        LocalContext.current.resources.displayMetrics.widthPixels.dp /
                LocalContext.current.resources.displayMetrics.density

    Box(modifier = Modifier.width(w - reduce)) {
        composable()
    }
}

@Composable
private fun TopBarIcon(
    icon: ImageVector,
    contentDescription: String,
    onTap: () -> Unit = {},
) {
    Icon(
        icon,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxHeight()
            .clickable {
                onTap()
            }
            .padding(10.dp)
    )
}

@Composable
private fun ActionButton(
    text: String? = null,
    icon: ImageVector? = null,
    onTap: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable { onTap() }
            .width(if (text == null) 36.dp else 100.dp)
            .height(28.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (text != null) {
            Text(
                text = text,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun SocialMediaSection(
    icon: ImageVector,
    text: String,
    onTap: () -> Unit = {}
) {
    Column(
        Modifier
            .padding(end = 20.dp)
            .clickable { onTap() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .size(70.dp)
                .padding(3.dp)
                .clip(CircleShape)
                .background(color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}


@ExperimentalFoundationApi
@Preview
@Composable
fun InstagramScreenPreview() {
    InstagramScreen()
}