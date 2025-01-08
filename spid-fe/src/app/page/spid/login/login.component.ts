import {Component, OnInit} from '@angular/core';
import {SpidService} from '../../../service/spid.service';

declare var SPID: any;

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  constructor(private spidService: SpidService) {
  }


  ngOnInit() {
    console.log(SPID.providers);
    SPID.init({
      lang: 'en',                   // opzionale
      selector: '#my-spid-button',  // opzionale
      method: 'POST',               // opzionale
      url: '/Login/{{idp}}',
      fieldName: 'idp',             // opzionale
      extraFields: {                // opzionale
        foo: 'bar',
        baz: 'baz'
      },
      mapping: {                    // opzionale
        'https://loginspid.aruba.it': 4,
        'https://posteid.poste.it': 5,
        'https://idp.namirialtsp.com/idp': 7,
      },
      supported: [                  // obbligatorio
        'https://identity.sieltecloud.it'
      ],
      extraProviders: [            // opzionale
        {
          "protocols": ["SAML"],
          "entityName": "valore de IDP entity ID",
          "logo": "spid-idp-aruba.svg",
          "entityID": "https://loginciccio.it",
          "active": true
        },
        {
          "protocols": ["SAML"],
          "entityName": "Foo bar ",
          "logo": "spid-idp-infocertid.svg",
          "entityID": "https://identity.pippocert.it",
          "active": true
        }
      ],
      protocol: "SAML",           // opzionale
      size: "small"               // opzionale
    });
  }

  redirect() {
    this.spidService.redirectToIdp("")
  }
}
