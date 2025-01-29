import {Component, OnInit} from '@angular/core';
import {CieService} from '../../../service/cie.service';
import {environment} from '../../../../environments/environment';

@Component({
  selector: 'app-charging-cie',
  imports: [],
  templateUrl: './charging-cie.component.html',
  styleUrl: './charging-cie.component.css'
})
export class ChargingCieComponent implements OnInit {

  constructor(private readonly cieService: CieService) {
  }

  ngOnInit() {
    this.cieService.getAuthRequest(environment.client_id).subscribe({
      next: (response: any) => {
      },
      error: err => {
        console.log(err);
      }
    })
  }

}
