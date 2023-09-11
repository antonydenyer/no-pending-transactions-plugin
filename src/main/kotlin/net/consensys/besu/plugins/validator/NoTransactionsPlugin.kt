package net.consensys.besu.plugins.validator

import com.google.auto.service.AutoService
import org.apache.logging.log4j.LogManager
import org.hyperledger.besu.plugin.BesuContext
import org.hyperledger.besu.plugin.BesuPlugin
import org.hyperledger.besu.plugin.data.EnodeURL
import org.hyperledger.besu.plugin.services.PermissioningService

@AutoService(BesuPlugin::class)
class NoTransactionsPlugin : BesuPlugin {
    private val logger = LogManager.getLogger()

    override fun register(context: BesuContext) {
        context.getService(PermissioningService::class.java).map {
            it.registerNodeMessagePermissioningProvider(this::isMessagePermitted)
        }
    }

    private fun isMessagePermitted(destinationEnode: EnodeURL, code: Int): Boolean {
        if (code == MessageCode.Transactions.code) {
            logger.info("Blocking Transactions to $destinationEnode")

            return false
        }
        return true
    }

    override fun start() {
    }

    override fun stop() {
    }
}
