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

import { AngularTestPage } from './app.po';
import { ExpectedConditions, browser, element, by } from 'protractor';
import {} from 'jasmine';


describe('Starting tests for new_net-angular-app', function() {
  let page: AngularTestPage;

  beforeEach(() => {
    page = new AngularTestPage();
  });

  it('website title should be new_net-angular-app', () => {
    page.navigateTo('/');
    return browser.getTitle().then((result)=>{
      expect(result).toBe('new_net-angular-app');
    })
  });

  it('network-name should be new_net@0.5.5',() => {
    element(by.css('.network-name')).getWebElement()
    .then((webElement) => {
      return webElement.getText();
    })
    .then((txt) => {
      expect(txt).toBe('new_net@0.5.5.bna');
    });
  });

  it('navbar-brand should be new_net-angular-app',() => {
    element(by.css('.navbar-brand')).getWebElement()
    .then((webElement) => {
      return webElement.getText();
    })
    .then((txt) => {
      expect(txt).toBe('new_net-angular-app');
    });
  });

  
    it('Player component should be loadable',() => {
      page.navigateTo('/Player');
      browser.findElement(by.id('assetName'))
      .then((assetName) => {
        return assetName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('Player');
      });
    });

    it('Player table should have 8 columns',() => {
      page.navigateTo('/Player');
      element.all(by.css('.thead-cols th')).then(function(arr) {
        expect(arr.length).toEqual(8); // Addition of 1 for 'Action' column
      });
    });
  
    it('Experiment1lc component should be loadable',() => {
      page.navigateTo('/Experiment1lc');
      browser.findElement(by.id('assetName'))
      .then((assetName) => {
        return assetName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('Experiment1lc');
      });
    });

    it('Experiment1lc table should have 7 columns',() => {
      page.navigateTo('/Experiment1lc');
      element.all(by.css('.thead-cols th')).then(function(arr) {
        expect(arr.length).toEqual(7); // Addition of 1 for 'Action' column
      });
    });
  
    it('Experiment2lc component should be loadable',() => {
      page.navigateTo('/Experiment2lc');
      browser.findElement(by.id('assetName'))
      .then((assetName) => {
        return assetName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('Experiment2lc');
      });
    });

    it('Experiment2lc table should have 7 columns',() => {
      page.navigateTo('/Experiment2lc');
      element.all(by.css('.thead-cols th')).then(function(arr) {
        expect(arr.length).toEqual(7); // Addition of 1 for 'Action' column
      });
    });
  
    it('Experiment2emg component should be loadable',() => {
      page.navigateTo('/Experiment2emg');
      browser.findElement(by.id('assetName'))
      .then((assetName) => {
        return assetName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('Experiment2emg');
      });
    });

    it('Experiment2emg table should have 6 columns',() => {
      page.navigateTo('/Experiment2emg');
      element.all(by.css('.thead-cols th')).then(function(arr) {
        expect(arr.length).toEqual(6); // Addition of 1 for 'Action' column
      });
    });
  
    it('Experiment3fp component should be loadable',() => {
      page.navigateTo('/Experiment3fp');
      browser.findElement(by.id('assetName'))
      .then((assetName) => {
        return assetName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('Experiment3fp');
      });
    });

    it('Experiment3fp table should have 8 columns',() => {
      page.navigateTo('/Experiment3fp');
      element.all(by.css('.thead-cols th')).then(function(arr) {
        expect(arr.length).toEqual(8); // Addition of 1 for 'Action' column
      });
    });
  
    it('Experiment3emg component should be loadable',() => {
      page.navigateTo('/Experiment3emg');
      browser.findElement(by.id('assetName'))
      .then((assetName) => {
        return assetName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('Experiment3emg');
      });
    });

    it('Experiment3emg table should have 6 columns',() => {
      page.navigateTo('/Experiment3emg');
      element.all(by.css('.thead-cols th')).then(function(arr) {
        expect(arr.length).toEqual(6); // Addition of 1 for 'Action' column
      });
    });
  

  
    it('Scientist component should be loadable',() => {
      page.navigateTo('/Scientist');
      browser.findElement(by.id('participantName'))
      .then((participantName) => {
        return participantName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('Scientist');
      });
    });

    it('Scientist table should have 5 columns',() => {
      page.navigateTo('/Scientist');
      element.all(by.css('.thead-cols th')).then(function(arr) {
        expect(arr.length).toEqual(5); // Addition of 1 for 'Action' column
      });
    });
  

  
    it('experiment1lcDataAdd component should be loadable',() => {
      page.navigateTo('/experiment1lcDataAdd');
      browser.findElement(by.id('transactionName'))
      .then((transactionName) => {
        return transactionName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('experiment1lcDataAdd');
      });
    });
  
    it('experiment2lcDataAdd component should be loadable',() => {
      page.navigateTo('/experiment2lcDataAdd');
      browser.findElement(by.id('transactionName'))
      .then((transactionName) => {
        return transactionName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('experiment2lcDataAdd');
      });
    });
  
    it('experiment2emgDataAdd component should be loadable',() => {
      page.navigateTo('/experiment2emgDataAdd');
      browser.findElement(by.id('transactionName'))
      .then((transactionName) => {
        return transactionName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('experiment2emgDataAdd');
      });
    });
  
    it('experiment3fpDataAdd component should be loadable',() => {
      page.navigateTo('/experiment3fpDataAdd');
      browser.findElement(by.id('transactionName'))
      .then((transactionName) => {
        return transactionName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('experiment3fpDataAdd');
      });
    });
  
    it('experiment3emgDataAdd component should be loadable',() => {
      page.navigateTo('/experiment3emgDataAdd');
      browser.findElement(by.id('transactionName'))
      .then((transactionName) => {
        return transactionName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('experiment3emgDataAdd');
      });
    });
  
    it('addPlayer component should be loadable',() => {
      page.navigateTo('/addPlayer');
      browser.findElement(by.id('transactionName'))
      .then((transactionName) => {
        return transactionName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('addPlayer');
      });
    });
  
    it('addScientist component should be loadable',() => {
      page.navigateTo('/addScientist');
      browser.findElement(by.id('transactionName'))
      .then((transactionName) => {
        return transactionName.getText();
      })
      .then((txt) => {
        expect(txt).toBe('addScientist');
      });
    });
  

});