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

import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as sinon from 'sinon';
import { DataService } from '../data.service';
import { experiment2lcDataAddComponent } from './experiment2lcDataAdd.component';
import {experiment2lcDataAddService} from './experiment2lcDataAdd.service';

describe('experiment2lcDataAddComponent', () => {
  let component: experiment2lcDataAddComponent;
  let fixture: ComponentFixture<experiment2lcDataAddComponent>;

  let mockexperiment2lcDataAddService;
  let mockDataService

  beforeEach(async(() => {

    mockexperiment2lcDataAddService = sinon.createStubInstance(experiment2lcDataAddService);
    mockexperiment2lcDataAddService.getAll.returns([]);
    mockDataService = sinon.createStubInstance(DataService);

    TestBed.configureTestingModule({
      declarations: [ experiment2lcDataAddComponent ],
      imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule
      ],
      providers: [
        {provide: experiment2lcDataAddService, useValue: mockexperiment2lcDataAddService },
        {provide: DataService, useValue: mockDataService },
      ]
    });

    fixture = TestBed.createComponent(experiment2lcDataAddComponent);
    component = fixture.componentInstance;

  }));


  it('should create', () => {
    expect(component).toBeTruthy();
  });

});

