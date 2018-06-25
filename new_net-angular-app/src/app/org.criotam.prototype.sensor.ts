import {Asset} from './org.hyperledger.composer.system';
import {Participant} from './org.hyperledger.composer.system';
import {Transaction} from './org.hyperledger.composer.system';
import {Event} from './org.hyperledger.composer.system';
// export namespace org.criotam.prototype.sensor{
   export class Scientist extends Participant {
      scientistId: string;
      userName: string;
      Email: string;
      Password: string;
   }
   export class Player extends Asset {
      player_id: string;
      player_name: string;
      player_age: string;
      player_weight: string;
      player_height: string;
      player_sex: string;
      experimenter: Scientist;
   }
   export class Experiment1lc extends Asset {
      experimentId: string;
      Raw_value: string;
      lc1_Raw_value: string[];
      lc2_Raw_value: string[];
      experimenter: Scientist;
      player: Player;
   }
   export class Experiment2lc extends Asset {
      experimentId: string;
      Raw_value: string;
      lc1_Raw_value: string[];
      lc2_Raw_value: string[];
      experimenter: Scientist;
      player: Player;
   }
   export class Experiment2emg extends Asset {
      experimentId: string;
      Raw_value: string;
      emg_Raw_value: string[];
      experimenter: Scientist;
      player: Player;
   }
   export class Experiment3fp extends Asset {
      experimentId: string;
      Raw_value: string;
      lc1_Raw_value: string[];
      lc2_Raw_value: string[];
      lc3_Raw_value: string[];
      experimenter: Scientist;
      player: Player;
   }
   export class Experiment3emg extends Asset {
      experimentId: string;
      Raw_value: string;
      emg_Raw_value: string[];
      experimenter: Scientist;
      player: Player;
   }
   export class experiment1lcDataAdd extends Transaction {
      experimentId: string;
      Raw_value: string;
      lc1_Raw_value: string[];
      lc2_Raw_value: string[];
   }
   export class experiment1lcDataread extends Event {
      experimentId: string;
   }
   export class experiment2lcDataAdd extends Transaction {
      experimentId: string;
      Raw_value: string;
      lc1_Raw_value: string[];
      lc2_Raw_value: string[];
   }
   export class experiment2lcDataread extends Event {
      experimentId: string;
   }
   export class experiment2emgDataAdd extends Transaction {
      experimentId: string;
      Raw_value: string;
      emg_Raw_value: string[];
   }
   export class experiment2emgDataread extends Event {
      experimentId: string;
   }
   export class experiment3fpDataAdd extends Transaction {
      experimentId: string;
      Raw_value: string;
      lc1_Raw_value: string[];
      lc2_Raw_value: string[];
      lc3_Raw_value: string[];
   }
   export class experiment3fpDataread extends Event {
      experimentId: string;
   }
   export class experiment3emgDataAdd extends Transaction {
      experimentId: string;
      Raw_value: string;
      emg_Raw_value: string[];
   }
   export class experiment3emgDataread extends Event {
      experimentId: string;
   }
   export class addPlayer extends Transaction {
      player_id: string;
      player_name: string;
      player_age: string;
      player_weight: string;
      player_height: string;
      player_sex: string;
   }
   export class addedPlayer extends Event {
      player_id: string;
   }
   export class addScientist extends Transaction {
      scientistId: string;
      userName: string;
      Email: string;
      Password: string;
   }
   export class addedScientist extends Event {
      scientistId: string;
   }
// }
