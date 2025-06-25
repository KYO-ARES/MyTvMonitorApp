import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject
import com.example.mytvmonitorapp.model.Tab
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MonitorRepository

) : ViewModel() {

    val tabs: Flow<List<Tab>> = repository.tabs
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}