package com.kriya.biosys.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kriya.biosys.R
import com.kriya.biosys.model.*
import com.kriya.biosys.viewmodel.*
import com.kriya.biosys.navigation.Screen

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavigate: (String) -> Unit) {
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
            text = "A biotechnology-driven ingredients partner delivering fermentation-based solutions for nutraceutical, food, feed, and pharma customers—fully available offline for field teams.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SectionTitle("Quick Links")
        val links = listOf(
            "Products" to Screen.Products.route,
            "Technology" to Screen.Technology.route,
            "Manufacturing" to Screen.Manufacturing.route,
            "Gallery" to Screen.Gallery.route,
            "Documents" to Screen.Documents.route,
            "Contact" to Screen.Contact.route
        )
        links.forEach { (label, route) ->
            ElevatedCard(
                onClick = { onNavigate(route) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            ) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(label, style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "Jump directly to $label details",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SectionTitle("Highlights")
        Text(
            text = "Use this offline app to showcase products, technology (Karyo & Wynn), manufacturing capabilities, CSR impact, and certifications without any connectivity.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun AboutScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("About Us") }
        item {
            Text(
                "Kriya Biosys engineers fermentation-led bio solutions that elevate yield, consistency, and sustainability for food, feed, nutraceutical, and industrial partners.",
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 22.sp
            )
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { SectionTitle("Mission") }
        item {
            Text(
                "To enable safer, cleaner, and scalable bio-based production through applied research, disciplined operations, and customer-centric technical support.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        item { SectionTitle("Vision") }
        item {
            Text(
                "To be the trusted innovation partner for fermentation-driven ingredients and process aids across global markets.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        item { SectionTitle("Strengths") }
        item {
            Text(
                "• Proven large-scale fermentation expertise\n" +
                    "• Integrated R&D, pilot, and manufacturing footprint\n" +
                    "• Rigor in quality, certifications, and compliance\n" +
                    "• Responsive sales and technical service teams",
                style = MaterialTheme.typography.bodyMedium
            )
        }
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
    val context = LocalContext.current
    val imageRes = remember(product.imageName) {
        context.resources.getIdentifier(product.imageName, "drawable", context.packageName)
    }
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            if (imageRes != 0) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Text(product.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(product.category, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
            Text(product.shortDescription, modifier = Modifier.padding(top = 8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Applications:", fontWeight = FontWeight.Bold)
            product.applications.forEach { Text("• $it") }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Specs:", fontWeight = FontWeight.Bold)
            product.specs.forEach { (k, v) -> Text("• $k: $v") }
        }
    }
}

@Composable
fun ManufacturingScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("Manufacturing / Factory") }
        item {
            Text(
                "In-house fermentation and downstream processing with controlled bioreactors, clean utilities, and QA labs enable consistent batches for enzymes, probiotics, and specialty ingredients.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item {
            Text(
                "Process Snapshot:\n• Upstream prep and inoculation\n• Optimized fermentation controls\n• Clarification and concentration\n• Spray/flash drying and packing\n• QA/QC with stability monitoring",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { Text("Include local photos and videos from drawable/raw respectively.") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnologyScreen(viewModel: TechnologyViewModel) {
    val techItems by viewModel.techItems.collectAsState()
    val tabs = listOf("Karyo", "Wynn")
    var selectedTab by remember { mutableStateOf(tabs.first()) }
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = tabs.indexOf(selectedTab)) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = selectedTab == title, onClick = { selectedTab = title }, text = { Text(title) })
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(techItems.filter { it.type.equals(selectedTab, ignoreCase = true) }) { item ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(item.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(item.summary, modifier = Modifier.padding(top = 6.dp))
                        if (item.details.isNotBlank()) {
                            Text(
                                item.details,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 6.dp)
                            )
                        }
                        Text("Highlights:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
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
            ElevatedCard(modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    val context = LocalContext.current
                    val drawableId = remember(member.photoName) {
                        context.resources.getIdentifier(member.photoName, "drawable", context.packageName)
                    }
                    if (drawableId != 0) {
                        Image(
                            painter = painterResource(id = drawableId),
                            contentDescription = member.name,
                            modifier = Modifier
                                .size(96.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(48.dp))
                        )
                    }
                    Text(member.name, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                    Text(member.role, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.secondary)
                    Text(member.bio, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
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
            ElevatedCard(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(activity.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Date: ${activity.date} | Year: ${activity.year}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
                    Text(activity.description, modifier = Modifier.padding(top = 6.dp))
                    val context = LocalContext.current
                    val preview = activity.imageNames.firstOrNull()?.let {
                        context.resources.getIdentifier(it, "drawable", context.packageName)
                    }
                    preview?.takeIf { it != 0 }?.let { resId ->
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = activity.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .padding(top = 8.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                        )
                    }
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
            data.factoryPhotos.forEach { img -> GalleryImageRow(img) }
            SectionTitle("Product Photos")
            data.productPhotos.forEach { img -> GalleryImageRow(img) }
            SectionTitle("Dispatch Photos")
            data.dispatchPhotos.forEach { img -> GalleryImageRow(img) }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
    }
}

@Composable
private fun GalleryImageRow(img: GalleryImage) {
    val context = LocalContext.current
    val drawableId = remember(img.imageName) {
        context.resources.getIdentifier(img.imageName, "drawable", context.packageName)
    }
    ElevatedCard(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), shape = RoundedCornerShape(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {
            if (drawableId != 0) {
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = img.title,
                    modifier = Modifier
                        .size(72.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                )
            }
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(img.title, style = MaterialTheme.typography.titleMedium)
                Text("Asset: ${img.imageName}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

@Composable
fun DocumentsScreen(viewModel: DocumentsViewModel) {
    val docs by viewModel.documents.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SectionTitle("Documents") }
        items(docs) { doc ->
            ElevatedCard(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(doc.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Type: ${doc.type}", color = MaterialTheme.colorScheme.secondary)
                    Text(doc.description, modifier = Modifier.padding(vertical = 6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Download, contentDescription = null)
                        Text("assets/docs/${doc.type.lowercase()}/${doc.fileName}", modifier = Modifier.padding(start = 6.dp))
                    }
                    Text("Tap to view locally once PDFs are added", style = MaterialTheme.typography.bodySmall)
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
