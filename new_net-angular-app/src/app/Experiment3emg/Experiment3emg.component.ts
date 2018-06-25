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
import { Experiment3emgService } from './Experiment3emg.service';
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'app-experiment3emg',
  templateUrl: './Experiment3emg.component.html',
  styleUrls: ['./Experiment3emg.component.css'],
  providers: [Experiment3emgService]
})
export class Experiment3emgComponent implements OnInit {

  myForm: FormGroup;

  private allAssets;
  private asset;
  private currentId;
  private errorMessage;

  experimentId = new FormControl('', Validators.required);
  Raw_value = new FormControl('', Validators.required);
  emg_Raw_value = new FormControl('', Validators.required);
  experimenter = new FormControl('', Validators.required);
  player = new FormControl('', Validators.required);

  constructor(private serviceExperiment3emg: Experiment3emgService, fb: FormBuilder) {
    this.myForm = fb.group({
      experimentId: this.experimentId,
      Raw_value: this.Raw_value,
      emg_Raw_value: this.emg_Raw_value,
      experimenter: this.experimenter,
      player: this.player
    });
  };

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll(): Promise<any> {
    const tempList = [];
    return this.serviceExperiment3emg.getAll()
    .toPromise()
    .then((result) => {
      this.errorMessage = null;
      result.forEach(asset => {
        tempList.push(asset);
      });
      this.allAssets = tempList;
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
   * @param {String} name - the name of the asset field to update
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
   * only). This is used for checkboxes in the asset updateDialog.
   * @param {String} name - the name of the asset field to check
   * @param {any} value - the enumeration value to check for
   * @return {Boolean} whether the specified asset field contains the provided value
   */
  hasArrayValue(name: string, value: any): boolean {
    return this[name].value.indexOf(value) !== -1;
  }

  addAsset(form: any): Promise<any> {
    this.asset = {
      $class: 'org.criotam.prototype.sensor.Experiment3emg',
      'experimentId': this.experimentId.value,
      'Raw_value': this.Raw_value.value,
      'emg_Raw_value': this.emg_Raw_value.value,
      'experimenter': this.experimenter.value,
      'player': this.player.value
    };

    this.myForm.setValue({
      'experimentId': null,
      'Raw_value': null,
      'emg_Raw_value': null,
      'experimenter': null,
      'player': null
    });

    return this.serviceExperiment3emg.addAsset(this.asset)
    .toPromise()
    .then(() => {
      this.errorMessage = null;
      this.myForm.setValue({
        'experimentId': null,
        'Raw_value': null,
        'emg_Raw_value': null,
        'experimenter': null,
        'player': null
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


  updateAsset(form: any): Promise<any> {
    this.asset = {
      $class: 'org.criotam.prototype.sensor.Experiment3emg',
      'Raw_value': this.Raw_value.value,
      'emg_Raw_value': this.emg_Raw_value.value,
      'experimenter': this.experimenter.value,
      'player': this.player.value
    };

    return this.serviceExperiment3emg.updateAsset(form.get('experimentId').value, this.asset)
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


  deleteAsset(): Promise<any> {

    return this.serviceExperiment3emg.deleteAsset(this.currentId)
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

    return this.serviceExperiment3emg.getAsset(id)
    .toPromise()
    .then((result) => {
      this.errorMessage = null;
      const formObject = {
        'experimentId': null,
        'Raw_value': null,
        'emg_Raw_value': null,
        'experimenter': null,
        'player': null
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

      if (result.emg_Raw_value) {
        formObject.emg_Raw_value = result.emg_Raw_value;
      } else {
        formObject.emg_Raw_value = null;
      }

      if (result.experimenter) {
        formObject.experimenter = result.experimenter;
      } else {
        formObject.experimenter = null;
      }

      if (result.player) {
        formObject.player = result.player;
      } else {
        formObject.player = null;
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
      'emg_Raw_value': null,
      'experimenter': null,
      'player': null
      });
  }

}
