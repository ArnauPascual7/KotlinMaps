package cat.itb.damv.m78

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapsScreen(){
    val markers = remember { mutableStateListOf<LatLng>() }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(40.41,-3.70), 5f)
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId("DEMO_MAP_ID")
            },
            onMapClick = { latLng ->
                markers.add(latLng)
            },
            cameraPositionState = cameraPositionState
        ) {
            AdvancedMarker(
                state = MarkerState(position = LatLng(-34.0, 151.0)),
                title = "Marker in Sydney"
            )
            AdvancedMarker(
                state = MarkerState(position = LatLng(35.66, 139.6)),
                title = "Marker in Tokyo"
            )
            markers.forEach { latLng ->
                AdvancedMarker(
                    state = MarkerState(position = latLng),
                    title = "Nuevo marcador"
                )
            }
        }
        Button(
            onClick = {
                cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(40.41,-3.70), 5f)
            }
        ) { }
    }
}