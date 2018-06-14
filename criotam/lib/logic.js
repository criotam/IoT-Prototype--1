/**
 * Create Scientist Transaction
 *
 */

const BusinessNetworkConnection = require('composer-client').BusinessNetworkConnection;

this.bizNetworkConnection = new BusinessNetworkConnection();
this.cardName = config.get('cardName');
this.businessNetworkIdentifier = config.get('businessNetworkIdentifier');

this.bizNetworkConnection.connect(this.cardName)
.then((result) => {
  this.businessNetworkDefinition = result;
});

this.bizNetworkConnection.getAssetRegistry('net.biz.digitalPropertyNetwork.LandTitle')
.then((result) => {
    this.titlesRegistry = result;
});


let factory = this.businessNetworkDefinition.getFactory();
owner = factory.newResource('net.biz.digitalPropertyNetwork', 'Person', 'PID:1234567890');
owner.firstName = 'Fred';
owner.lastName = 'Bloggs';