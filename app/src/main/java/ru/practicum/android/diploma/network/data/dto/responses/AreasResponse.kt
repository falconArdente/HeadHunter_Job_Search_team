package ru.practicum.android.diploma.network.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.AreaDTO

class AreasResponse(
    @SerializedName("areas") val areasList: List<AreaDTO>
) : Response()
