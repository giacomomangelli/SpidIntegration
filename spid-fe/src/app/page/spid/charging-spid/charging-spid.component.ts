import {Component, OnInit} from '@angular/core';
import {SpidService} from '../../../service/spid.service';
import {environment} from '../../../../environments/environment';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-charging-spid',
  imports: [],
  templateUrl: './charging-spid.component.html',
  styleUrl: './charging-spid.component.css'
})
export class ChargingSpidComponent implements OnInit {

  private readonly idp: string

  constructor(private readonly spidService: SpidService,
              private readonly route: ActivatedRoute) {
    this.idp = this.route.snapshot.params['idp'];
  }

  ngOnInit(): void {
    console.log(this.idp);
    this.spidService.getMetadata(environment.client_id).subscribe({
      next: data => {
        console.log("METADATA: ", data);
        this.spidService.getAuthRequest(environment.client_id, this.idp).subscribe({
          next: ssoData => {
            window.open(ssoData.sso_request, "_self");
          },
          error: () => {
            console.log("error", data);
          },
          complete: () => {
            console.log("Redirecting...");
          }
        })
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        console.log("Waiting...");
      }
    });
  }
}
