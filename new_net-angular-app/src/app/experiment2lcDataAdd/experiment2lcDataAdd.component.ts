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
import { experiment2lcDataAddService } from './experiment2lcDataAdd.service';
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'app-experiment2lcdataadd',
  templateUrl: './experiment2lcDataAdd.component.html',
  styleUrls: ['./experiment2lcDataAdd.component.css'],
  providers: [experiment2lcDataAddService]
})
export class experiment2lcDataAddComponent implements OnInit {

  myForm: FormGroup;

  private allTransactions;
  private Transaction;
  private currentId;
  private errorMessage;

  experimentId = new FormControl('', Validators.required);
  Raw_value = new FormControl('', Validators.required);
  lc1_Raw_value = new FormControl('', Validators.required);
  lc2_Raw_value = new FormControl('', Validators.required);
  transactionId = new FormControl('', Validators.required);
  timestamp = new FormControl('', Validators.required);


  constructor(private serviceexperiment2lcDataAdd: experiment2lcDataAddService, fb: FormBuilder) {
    this.myForm = fb.group({
      experimentId: this.experimentId,
      Raw_value: this.Raw_value,
      lc1_Raw_value: this.lc1_Raw_value,
      lc2_Raw_value: this.lc2_Raw_value,
      transactionId: this.transactionId,
      timestamp: this.timestamp
    });
  };

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll(): Promise<any> {
    const tempList = [];
    return this.serviceexperiment2lcDataAdd.getAll()
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
      $class: 'org.criotam.prototype.sensor.experiment2lcDataAdd',
      'experimentId': this.experimentId.value,
      'Raw_value': this.Raw_value.value,
      'lc1_Raw_value': this.lc1_Raw_value.value,
      'lc2_Raw_value': this.lc2_Raw_value.value,
      'transactionId': this.transactionId.value,
      'timestamp': this.timestamp.value
    };

    this.myForm.setValue({
      'experimentId': null,
      'Raw_value': null,
      'lc1_Raw_value': null,
      'lc2_Raw_value': null,
      'transactionId': null,
      'timestamp': null
    });

    return this.serviceexperiment2lcDataAdd.addTransaction(this.Transaction)
    .toPromise()
    .then(() => {
      this.errorMessage = null;
      this.myForm.setValue({
        'experimentId': null,
        'Raw_value': null,
        'lc1_Raw_value': null,
        'lc2_Raw_value': null,
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
      $class: 'org.criotam.prototype.sensor.experiment2lcDataAdd',
      'experimentId': this.experimentId.value,
      'Raw_value': this.Raw_value.value,
      'lc1_Raw_value': this.lc1_Raw_value.value,
      'lc2_Raw_value': this.lc2_Raw_value.value,
      'timestamp': this.timestamp.value
    };

    return this.serviceexperiment2lcDataAdd.updateTransaction(form.get('transactionId').value, this.Transaction)
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

    return this.serviceexperiment2lcDataAdd.deleteTransaction(this.currentId)
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

    return this.serviceexperiment2lcDataAdd.getTransaction(id)
    .toPromise()
    .then((result) => {
      this.errorMessage = null;
      const formObject = {
        'experimentId': null,
        'Raw_value': null,
        'lc1_Raw_value': null,
        'lc2_Raw_value': null,
        'transactionId': null,
        'timestamp': null
      };

      if (result.experimentId) {
        formObject.experimentId = result.experimentId;
      } else {
        formObject.experimentId = null;
      }

      if (result.Raw_value) {
        formObject.Raw_value = result.Raw_value;
      } else {
        formObject.Raw_value = null;
      }

      if (result.lc1_Raw_value) {
        formObject.lc1_Raw_value = result.lc1_Raw_value;
      } else {
        formObject.lc1_Raw_value = null;
      }

      if (result.lc2_Raw_value) {
        formObject.lc2_Raw_value = result.lc2_Raw_value;
      } else {
        formObject.lc2_Raw_value = null;
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
      'experimentId': null,
      'Raw_value': null,
      'lc1_Raw_value': null,
      'lc2_Raw_value': null,
      'transactionId': null,
      'timestamp': null
    });
  }
}
