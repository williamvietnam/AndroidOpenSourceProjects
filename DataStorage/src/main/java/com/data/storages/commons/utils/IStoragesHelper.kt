package com.data.storages.commons.utils

import com.data.storages.features.externalStorage.data.IExternalStorageHelper
import com.data.storages.features.internalStorage.data.IInternalStorageHelper
import com.data.storages.features.roomDatabase.data.IDatabaseStorageHelper
import com.data.storages.features.servers.data.apis.IApiHelper

interface IStoragesHelper : IInternalStorageHelper, IExternalStorageHelper,
    IDatabaseStorageHelper, IApiHelper {

}