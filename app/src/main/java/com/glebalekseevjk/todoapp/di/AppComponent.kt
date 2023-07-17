package com.glebalekseevjk.todoapp.di

import android.content.Context
import com.glebalekseevjk.core.utils.di.ApplicationContext
import com.glebalekseevjk.todoapp.broadcastreceiver.NotificationReceiver
import com.glebalekseevjk.feature.auth.di.AuthDependencies
import com.glebalekseevjk.feature.todoitem.di.TodoItemDependencies
import com.glebalekseevjk.todoapp.App
import com.glebalekseevjk.todoapp.MainActivity
import com.glebalekseevjk.todoapp.broadcastreceiver.BootCompleteReceiver
import com.glebalekseevjk.todoapp.broadcastreceiver.SetAsideADayReceiver
import com.glebalekseevjk.todoapp.broadcastreceiver.TimeChangedReceiver
import com.glebalekseevjk.todoapp.di.module.AuthDependenciesModule
import com.glebalekseevjk.todoapp.di.module.BroadcastReceiverModule
import com.glebalekseevjk.todoapp.di.module.LocalDataSourceModule
import com.glebalekseevjk.todoapp.di.module.RemoteDataSourceModule
import com.glebalekseevjk.todoapp.di.module.RepositoryModule
import com.glebalekseevjk.todoapp.di.module.TodoItemDependenciesModule
import com.glebalekseevjk.todoapp.di.scope.AppComponentScope
import com.glebalekseevjk.todoapp.worker.SynchronizeWorker
import dagger.BindsInstance
import dagger.Component


@AppComponentScope
@Component(
    modules = [
        RepositoryModule::class,
        LocalDataSourceModule::class,
        RemoteDataSourceModule::class,
        AuthDependenciesModule::class,
        TodoItemDependenciesModule::class,
        BroadcastReceiverModule::class
    ]
)
interface AppComponent : AuthDependencies, TodoItemDependencies {
    fun inject(application: App)
    fun inject(bootCompleteReceiver: BootCompleteReceiver)
    fun inject(timeChangedReceiver: TimeChangedReceiver)
    fun inject(mainActivity: MainActivity)
    fun inject(synchronizeWorker: SynchronizeWorker)
    fun inject(notificationReceiver: NotificationReceiver)
    fun inject(setAsideADayReceiver: SetAsideADayReceiver)

    @Component.Factory
    interface Factory {
        fun create(@[ApplicationContext BindsInstance] context: Context): AppComponent
    }
}