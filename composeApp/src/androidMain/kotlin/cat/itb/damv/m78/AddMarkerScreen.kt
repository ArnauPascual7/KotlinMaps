package cat.itb.damv.m78

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddMarkerScreen(
    viewModel: MarkersViewModel = viewModel(),
    onAddMarker: (MarkerData) -> Unit,
    onCancel: () -> Unit
) {
    /*var lat by remember { mutableStateOf("") }
    var lng by remember { mutableStateOf("") }*/
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val latLng = viewModel.newMarkerLatLng.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Afegir un marcador", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        /*TextField(
            value = lat,
            onValueChange = { lat = it },
            label = { Text("Latitud") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = lng,
            onValueChange = { lng = it },
            label = { Text("Longitud") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )*/
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Títol") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripció") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                if (title.isNotEmpty()) {
                    val newMarker = MarkerData(
                        latLng = latLng,
                        title = title,
                        description = description,
                    )
                    onAddMarker(newMarker)
                    viewModel.markers.add(newMarker)
                } else {
                    errorMessage = "Has d'afegir un títol al marcador"
                }
            }) {
                Text("Afegir")
            }
            Button(onClick = { onCancel() }) {
                Text("Cancelar")
            }
        }
    }
}
