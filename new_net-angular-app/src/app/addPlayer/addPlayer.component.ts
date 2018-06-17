/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { addPlayerService } from './addPlayer.service';
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'app-addplayer',
  templateUrl: './addPlayer.component.html',
  styleUrls: ['./addPlayer.component.css'],
  providers: [addPlayerService]
})
export class addPlayerComponent implements OnInit {

  myForm: FormGroup;

  private allTransactions;
  private Transaction;
  private currentId;
  private errorMessage;

  player_id = new FormControl('', Validators.required);
  player_name = new FormControl('', Validators.required);
  player_age = new FormControl('', Validators.required);
  player_weight = new FormControl('', Validators.required);
  player_height = new FormControl('', Validators.required);
  player_sex = new FormControl('', Validators.required);
  transactionId = new FormControl('', Validators.required);
  timestamp = new FormControl('', Validators.required);


  constructor(private serviceaddPlayer: addPlayerService, fb: FormBuilder) {
    this.myForm = fb.group({
      player_id: this.player_id,
      player_name: this.player_name,
      player_age: this.player_age,
      player_weight: this.player_weight,
      player_height: this.player_height,
      player_sex: this.player_sex,
      transactionId: this.transactionId,
      timestamp: this.timestamp
    });
  };

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll(): Promise<any> {
    const tempList = [];
    return this.serviceaddPlayer.getAll()
    .toPromise()
    .then((result) => {
      this.errorMessage = null;
      result.forEach(transaction => {
        tempList.push(transaction);
      });
      this.allTransactions = tempList;
    })
    .catch((error) => {
      if (error === 'Server error') {
        this.errorMessage = 'Could not connect to REST server. Please check your configuration details';
      } else if (error === '404 - Not Found') {
        this.errorMessage = '404 - Could not find API route. Please check your available APIs.';
      } else {
        this.errorMessage = error;
      }
    });
  }

	/**
   * Event handler for changing the checked state of a checkbox (handles array enumeration values)
   * @param {String} name - the name of the transaction field to update
   * @param {any} value - the enumeration value for which to toggle the checked state
   */
  changeArrayValue(name: string, value: any): void {
    const index = this[name].value.indexOf(value);
    if (index === -1) {
      this[name].value.push(value);
    } else {
      this[name].value.splice(index, 1);
    }
  }

	/**
	 * Checkbox helper, determining whether an enumeration value should be selected or not (for array enumeration values
   * only). This is used for checkboxes in the transaction updateDialog.
   * @param {String} name - the name of the transaction field to check
   * @param {any} value - the enumeration value to check for
   * @return {Boolean} whether the specified transaction field contains the provided value
   */
  hasArrayValue(name: string, value: any): boolean {
    return this[name].value.indexOf(value) !== -1;
  }

  addTransaction(form: any): Promise<any> {
    this.Transaction = {
      $class: 'org.criotam.prototype.sensor.addPlayer',
      'player_id': this.player_id.value,
      'player_name': this.player_name.value,
      'player_age': this.player_age.value,
      'player_weight': this.player_weight.value,
      'player_height': this.player_height.value,
      'player_sex': this.player_sex.value,
      'transactionId': this.transactionId.value,
      'timestamp': this.timestamp.value
    };

    this.myForm.setValue({
      'player_id': null,
      'player_name': null,
      'player_age': null,
      'player_weight': null,
      'player_height': null,
      'player_sex': null,
      'transactionId': null,
      'timestamp': null
    });

    return this.serviceaddPlayer.addTransaction(this.Transaction)
    .toPromise()
    .then(() => {
      this.errorMessage = null;
      this.myForm.setValue({
        'player_id': null,
        'player_name': null,
        'player_age': null,
        'player_weight': null,
        'player_height': null,
        'player_sex': null,
        'transactionId': null,
        'timestamp': null
      });
    })
    .catch((error) => {
      if (error === 'Server error') {
        this.errorMessage = 'Could not connect to REST server. Please check your configuration details';
      } else {
        this.errorMessage = error;
      }
    });
  }

  updateTransaction(form: any): Promise<any> {
    this.Transaction = {
      $class: 'org.criotam.prototype.sensor.addPlayer',
      'player_id': this.player_id.value,
      'player_name': this.player_name.value,
      'player_age': this.player_age.value,
      'player_weight': this.player_weight.value,
      'player_height': this.player_height.value,
      'player_sex': this.player_sex.value,
      'timestamp': this.timestamp.value
    };

    return this.serviceaddPlayer.updateTransaction(form.get('transactionId').value, this.Transaction)
    .toPromise()
    .then(() => {
      this.errorMessage = null;
    })
    .catch((error) => {
      if (error === 'Server error') {
        this.errorMessage = 'Could not connect to REST server. Please check your configuration details';
      } else if (error === '404 - Not Found') {
      this.errorMessage = '404 - Could not find API route. Please check your available APIs.';
      } else {
        this.errorMessage = error;
      }
    });
  }

  deleteTransaction(): Promise<any> {

    return this.serviceaddPlayer.deleteTransaction(this.currentId)
    .toPromise()
    .then(() => {
      this.errorMessage = null;
    })
    .catch((error) => {
      if (error === 'Server error') {
        this.errorMessage = 'Could not connect to REST server. Please check your configuration details';
      } else if (error === '404 - Not Found') {
        this.errorMessage = '404 - Could not find API route. Please check your available APIs.';
      } else {
        this.errorMessage = error;
      }
    });
  }

  setId(id: any): void {
    this.currentId = id;
  }

  getForm(id: any): Promise<any> {

    return this.serviceaddPlayer.getTransaction(id)
    .toPromise()
    .then((result) => {
      this.errorMessage = null;
      const formObject = {
        'player_id': null,
        'player_name': null,
        'player_age': null,
        'player_weight': null,
        'player_height': null,
        'player_sex': null,
        'transactionId': null,
        'timestamp': null
      };

      if (result.player_id) {
        formObject.player_id = result.player_id;
      } else {
        formObject.player_id = null;
      }

      if (result.player_name) {
        formObject.player_name = result.player_name;
      } else {
        formObject.player_name = null;
      }

      if (result.player_age) {
        formObject.player_age = result.player_age;
      } else {
        formObject.player_age = null;
      }

      if (result.player_weight) {
        formObject.player_weight = result.player_weight;
      } else {
        formObject.player_weight = null;
      }

      if (result.player_height) {
        formObject.player_height = result.player_height;
      } else {
        formObject.player_height = null;
      }

      if (result.player_sex) {
        formObject.player_sex = result.player_sex;
      } else {
        formObject.player_sex = null;
      }

      if (result.transactionId) {
        formObject.transactionId = result.transactionId;
      } else {
        formObject.transactionId = null;
      }

      if (result.timestamp) {
        formObject.timestamp = result.timestamp;
      } else {
        formObject.timestamp = null;
      }

      this.myForm.setValue(formObject);

    })
    .catch((error) => {
      if (error === 'Server error') {
        this.errorMessage = 'Could not connect to REST server. Please check your configuration details';
      } else if (error === '404 - Not Found') {
      this.errorMessage = '404 - Could not find API route. Please check your available APIs.';
      } else {
        this.errorMessage = error;
      }
    });
  }

  resetForm(): void {
    this.myForm.setValue({
      'player_id': null,
      'player_name': null,
      'player_age': null,
      'player_weight': null,
      'player_height': null,
      'player_sex': null,
      'transactionId': null,
      'timestamp': null
    });
  }
}
