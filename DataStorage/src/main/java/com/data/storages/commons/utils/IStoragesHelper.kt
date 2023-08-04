package com.data.storages.commons.utils

import com.data.storages.storageModule.externalStorage.IExternalStorageHelper
import com.data.storages.storageModule.internalStorage.IInternalStorageHelper
import com.data.storages.storageModule.roomDatabase.IDatabaseStorageHelper
import com.data.storages.storageModule.servers.apis.IApiHelper

interface IStoragesHelper : IInternalStorageHelper,
    IExternalStorageHelper,
    IDatabaseStorageHelper,
    IApiHelper {

}