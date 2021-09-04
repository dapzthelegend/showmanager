/**
 * Created by Sola-Aremu Oluwadara on 04/09/2021.
 */

package com.example.showmanager.features.addshow.data

import co.crop2cash.core.network.utils.Failure

sealed class ShowsError : Failure.FeatureFailure() {

    object NoMoreShows : ShowsError()
}
