import {Component, OnInit} from '@angular/core';
import {SpidService} from '../../../service/spid.service';
import {CieService} from '../../../service/cie.service';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';

declare var SPID: any;

@Component({
  selector: 'app-login',
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  constructor(private readonly spidService: SpidService,
              private readonly cieService: CieService,
              private readonly router: Router,) {
  }

  ngOnInit() {
    console.log(SPID.providers);
    SPID.init({
      lang: 'it',
      selector: '#my-spid-button',
      method: "GET",
      url: "http://localhost:4200/spid/charging-spid/{{idp}}",
      mapping: {
        'https://loginspid.aruba.it': 'arubaid',
        'https://posteid.poste.it': 'poste',
        'https://idp.namirialtsp.com/idp': 'namirialid',
        'https://identity.sieltecloud.it': 'sielte',
        'https://identity.infocert.it': 'infocert',
        'https://login.id.tim.it/affwebservices/public/saml2sso': 'tim',
        'https://spid.register.it': 'spiditalia',
        'https://spid.intesa.it': 'intesa',
        'https://id.lepida.it/idp/shibboleth': 'lepida'
      },
      supported: [
        'https://identity.sieltecloud.it',
        'https://loginspid.aruba.it',
        'https://posteid.poste.it',
        'https://identity.infocert.it',
        'https://login.id.tim.it/affwebservices/public/saml2sso',
        'https://idp.namirialtsp.com/idp',
        'https://spid.register.it',
        'https://spid.intesa.it',
        'https://id.lepida.it/idp/shibboleth'
      ],
    });

  }

  cieConnect(): void {
    // this.router.navigate(['/spid/charging-cie']).then();
    window.open("http://localhost:8080/spid/test2", "_self");
    // this.spidService.test().subscribe(t => {
    //   console.log(t);
    // })
  }

}
