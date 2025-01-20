import {Component, OnInit} from '@angular/core';
import {environment} from '../../../../environments/environment';
import {CieService} from '../../../service/cie.service';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-charging-cie',
  imports: [],
  templateUrl: './charging-cie.component.html',
  styleUrl: './charging-cie.component.css'
})
export class ChargingCieComponent implements OnInit {
  showIframe = false;
  public safeUrl: SafeResourceUrl;


  constructor(private readonly cieService: CieService, private sanitizer: DomSanitizer) {
    const externalUrl = 'https://www.google.com';
    this.safeUrl = this.sanitizer.bypassSecurityTrustResourceUrl(externalUrl);
  }

  ngOnInit() {
    // this.cieService.getAuthRequest(environment.client_id).subscribe({
    //   next: (response: any) => {},
    //   error: err => {
    //     console.log(err);
    //   }
    // })
  }

  loadGoogle() {
    this.showIframe = true;
  }

}
