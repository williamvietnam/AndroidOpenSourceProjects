package com.android.vncalling.stringee

import com.android.vncalling.MyApp
import com.android.vncalling.data.DataManager
import com.android.vncalling.data.DataManagerImplement
import com.android.vncalling.utilities.rx.SchedulerProvider
import com.android.vncalling.utilities.rx.SchedulerProviderImplement
import com.stringee.StringeeClient
import com.stringee.call.StringeeCall
import com.stringee.call.StringeeCall2
import com.stringee.exception.StringeeError
import com.stringee.listener.StringeeConnectionListener
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.json.JSONObject

class StringEeImplement(
    private val client: StringeeClient = StringeeClient(MyApp.appContext),
    private val dataManager: DataManager = DataManagerImplement(),
    private val schedulerProvider: SchedulerProvider = SchedulerProviderImplement(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : StringEeHelper {

    override fun getClient(): StringeeClient {
        return this.client
    }

    override fun connectToServer(token: String?, connectionListener: StringeeConnectionListener?) {
        client.setConnectionListener(this)
        if (connectionListener != null) {
            client.setConnectionListener(connectionListener)
        }
        client.connect(token)
    }

    override fun connectToServer(token: String?) {
        connectToServer(token, null)
    }

    override fun onConnectionConnected(p0: StringeeClient?, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onConnectionDisconnected(p0: StringeeClient?, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onIncomingCall(p0: StringeeCall?) {
        TODO("Not yet implemented")
    }

    override fun onIncomingCall2(p0: StringeeCall2?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionError(p0: StringeeClient?, p1: StringeeError?) {
        TODO("Not yet implemented")
    }

    override fun onRequestNewToken(p0: StringeeClient?) {
        TODO("Not yet implemented")
    }

    override fun onCustomMessage(p0: String?, p1: JSONObject?) {
        TODO("Not yet implemented")
    }

    override fun onTopicMessage(p0: String?, p1: JSONObject?) {
        TODO("Not yet implemented")
    }

}