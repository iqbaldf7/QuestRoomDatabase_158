package com.example.activity7.dependeciesinjection

import android.content.Context
import android.net.wifi.WifiManager.InterfaceCreationImpact
import androidx.activity.result.contract.ActivityResultContracts
import com.example.activity7.Repository.LocalRepositoryMhs
import com.example.activity7.Repository.RepositoryMhs
import com.example.activity7.data.database.KrsDatabase

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}
class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahahasiwa())
    }

}