package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.VideoView
import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api

data class Prato(
    val nome: String,
    val preco: String,
    val tempoPreparo: String,
    val ingredientes: String,
    val disponivel: Boolean,
    val categoria: String,
    val imagemRes: Int,
    val temVideoChef: Boolean = false
)

val pratos = listOf(
    Prato("Risoto", "R$25", "20 min", "Arroz arbório, queijo, ervas", true, "Principal", R.drawable.risoto),
    Prato("Filé à Parmegiana", "R$32", "25 min", "Filé bovino, queijo, molho de tomate", true, "Principal", R.drawable.parmegiana, temVideoChef = true),
    Prato("Salada Caesar", "R$18", "10 min", "Alface, frango grelhado, parmesão", true, "Entrada", R.drawable.caesar),
    Prato("Sopa de Abóbora", "R$15", "15 min", "Abóbora, creme de leite, cebola", false, "Entrada", R.drawable.sopa),
    Prato("Brownie com Sorvete", "R$16", "8 min", "Chocolate, sorvete de creme, castanhas", true, "Sobremesa", R.drawable.brownie),
    Prato("Suco Detox", "R$10", "5 min", "Couve, limão, maçã, gengibre", true, "Bebida", R.drawable.suco)
)

val categorias = listOf("Principal", "Entrada", "Sobremesa", "Bebida")

@Composable
fun CardapioDigitalScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var showChefVideo by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {
        // Vídeo do ambiente (topo)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                factory = {
                    VideoView(it).apply {
                        setVideoURI(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.ambiente))
                        setOnPreparedListener { mp -> mp.isLooping = true; start() }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            categorias.forEach { categoria ->
                val pratosCategoria = pratos.filter { it.categoria == categoria }
                if (pratosCategoria.isNotEmpty()) {
                    item {
                        Text(
                            text = categoria,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    items(pratosCategoria) { prato ->
                        PratoCard(prato, onAssistaClick = { showChefVideo = true })
                    }
                }
            }
        }
        // Vídeo do chef (modal simples)
        if (showChefVideo) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0xAA000000))
                    .clickable { showChefVideo = false },
                contentAlignment = Alignment.Center
            ) {
                Box(Modifier.size(300.dp, 200.dp).background(Color.Black)) {
                    AndroidView(
                        factory = {
                            VideoView(it).apply {
                                setVideoURI(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.chef))
                                setOnPreparedListener { mp -> mp.isLooping = false; start() }
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun PratoCard(prato: Prato, onAssistaClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = prato.imagemRes),
                contentDescription = "Imagem do prato ${prato.nome}",
                modifier = Modifier.size(80.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(prato.nome, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(prato.preco, color = Color(0xFF388E3C), fontWeight = FontWeight.Bold)
                Text("Tempo: ${prato.tempoPreparo}", fontSize = 12.sp)
                Text("Ingredientes: ${prato.ingredientes}", fontSize = 12.sp)
                Text(
                    if (prato.disponivel) "Disponível" else "Indisponível",
                    color = if (prato.disponivel) Color(0xFF388E3C) else Color.Red,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            }
            if (prato.temVideoChef) {
                Button(onClick = onAssistaClick, modifier = Modifier.padding(start = 8.dp)) {
                    Text("Assista")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
class RestauranteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Restaurante") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(painterResource(android.R.drawable.ic_menu_revert), contentDescription = "Voltar")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    CardapioDigitalScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
} 