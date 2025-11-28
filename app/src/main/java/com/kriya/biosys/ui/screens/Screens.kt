package com.kriya.biosys.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kriya.biosys.R
import com.kriya.biosys.model.*
import com.kriya.biosys.viewmodel.*

@Composable
fun SectionTitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_kriya),
            contentDescription = "Kriya logo",
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Kriya Biosys",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Placeholder introduction for the offline experience. // TODO: Replace with concise company intro from website.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SectionTitle("Quick Links")
        val links = listOf("Products", "Technology", "Gallery", "Documents", "Contact")
        links.forEach {
            OutlinedCard(onClick = { /* navigation handled by bottom bar */ }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(it, modifier = Modifier.weight(1f))
                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SectionTitle("Highlights")
        Text(
            text = "Use this offline app to showcase products, technology (Karyo & Wynn), manufacturing, CSR, and documentation without internet.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun AboutScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("About Us") }
        item {
            Text("Company overview placeholder. // TODO: Replace with actual overview from website.", style = MaterialTheme.typography.bodyLarge)
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { SectionTitle("Mission") }
        item { Text("Mission statement placeholder.") }
        item { SectionTitle("Vision") }
        item { Text("Vision statement placeholder.") }
        item { SectionTitle("Strengths") }
        item { Text("Bullet points of strengths placeholder.") }
    }
}

@Composable
fun ProductsScreen(viewModel: ProductsViewModel) {
    val products by viewModel.products.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("Products") }
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(product.name, style = MaterialTheme.typography.titleLarge)
            Text(product.category, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
            Text(product.shortDescription, modifier = Modifier.padding(top = 8.dp))
            Text("Applications: ${product.applications.joinToString()}", style = MaterialTheme.typography.bodyMedium)
            Text("Specs:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
            product.specs.forEach { (k, v) ->
                Text("• $k: $v", style = MaterialTheme.typography.bodyMedium)
            }
            Text("// TODO: Link to detail page if needed", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Composable
fun ManufacturingScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("Manufacturing / Factory") }
        item { Text("Overview placeholder text for manufacturing capabilities. // TODO: Replace with detailed process and certifications.") }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { Text("Process steps placeholder.") }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { Text("Include local photos and videos from drawable/raw respectively.") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnologyScreen(viewModel: TechnologyViewModel) {
    val techItems by viewModel.techItems.collectAsState()
    val tabs = listOf("Karyo", "Wynn")
    var selectedTab by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(tabs.first()) }
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = tabs.indexOf(selectedTab)) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = selectedTab == title, onClick = { selectedTab = title }, text = { Text(title) })
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(techItems.filter { it.type.equals(selectedTab, ignoreCase = true) }) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(item.title, style = MaterialTheme.typography.titleLarge)
                        Text(item.summary)
                        Text("Details: ${item.details} // TODO: Replace with actual ${item.title} narrative from website.")
                        Text("Highlights:")
                        item.highlights.forEach { Text("• $it") }
                    }
                }
            }
        }
    }
}

@Composable
fun TeamScreen(viewModel: TeamViewModel) {
    val members by viewModel.team.collectAsState()
    LazyVerticalGrid(columns = GridCells.Adaptive(160.dp), modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(members) { member ->
            Card(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(model = member.photoName, contentDescription = member.name, modifier = Modifier.size(80.dp))
                    Text(member.name, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
                    Text(member.role, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    Text(member.bio, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun CSRScreen(viewModel: CSRViewModel) {
    val activities by viewModel.activities.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("CSR Activities") }
        items(activities) { activity ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(activity.title, style = MaterialTheme.typography.titleLarge)
                    Text("Date: ${activity.date} | Year: ${activity.year}", style = MaterialTheme.typography.bodyMedium)
                    Text(activity.description)
                    Text("Images: ${activity.imageNames.joinToString()} (replace with drawable resources)")
                }
            }
        }
    }
}

@Composable
fun GalleryScreen(viewModel: GalleryViewModel) {
    val gallery by viewModel.gallery.collectAsState()
    gallery?.let { data ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            SectionTitle("Gallery")
            SectionTitle("Factory Videos")
            data.factoryVideos.forEach { video ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(video.title, style = MaterialTheme.typography.titleLarge)
                        Text("Video file: ${video.videoResName} in res/raw")
                        Text("TODO: Hook up ExoPlayer for playback of raw resource")
                    }
                }
            }
            SectionTitle("Factory Photos")
            data.factoryPhotos.forEach { img -> Text("• ${img.title} (drawable: ${img.imageName})") }
            SectionTitle("Product Photos")
            data.productPhotos.forEach { img -> Text("• ${img.title} (drawable: ${img.imageName})") }
            SectionTitle("Dispatch Photos")
            data.dispatchPhotos.forEach { img -> Text("• ${img.title} (drawable: ${img.imageName})") }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
    }
}

@Composable
fun DocumentsScreen(viewModel: DocumentsViewModel) {
    val docs by viewModel.documents.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("Documents") }
        items(docs) { doc ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(doc.title, style = MaterialTheme.typography.titleLarge)
                    Text("Type: ${doc.type}")
                    Text(doc.description)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Download, contentDescription = null)
                        Text("File: assets/docs/${doc.type.lowercase()}/${doc.fileName}")
                    }
                    Text("TODO: Connect PDF viewer to load this asset when tapped")
                }
            }
        }
    }
}

@Composable
fun ContactScreen(viewModel: ContactViewModel) {
    val info = viewModel.contactInfo
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SectionTitle("Contact Us")
        Text("Corporate Address:\n${info.corporateAddress}")
        Spacer(modifier = Modifier.height(8.dp))
        SectionTitle("Factories")
        info.factoryAddresses.forEach { Text("• $it") }
        Spacer(modifier = Modifier.height(8.dp))
        SectionTitle("Phones")
        info.phones.forEach { phone ->
            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                context.startActivity(intent)
            }) { Text(phone) }
        }
        SectionTitle("Emails")
        info.emails.forEach { email ->
            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
                context.startActivity(intent)
            }) { Text(email) }
        }
        info.qrImageName?.let {
            SectionTitle("Map / QR")
            Text("Replace drawable $it with QR or static map image.")
        }
    }
}
