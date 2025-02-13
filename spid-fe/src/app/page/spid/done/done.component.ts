import {Component, OnInit} from '@angular/core';
import {UserSpidData} from '../../../model/userdata';
import {CallbackService} from '../../../service/callback.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-done',
  imports: [],
  templateUrl: './done.component.html',
  styleUrl: './done.component.css'
})
export class DoneComponent implements OnInit {

  userSpidData: UserSpidData;
  dataId: string;

  ngOnInit() {
    this.callbackService.getSpidUserData(this.dataId).subscribe({
        next: res => {
          this.userSpidData = res;
        },
        error: err => {
          console.log(err);
        }
      })
  }

  constructor(private readonly callbackService: CallbackService,
              private readonly route: ActivatedRoute) {
    this.dataId = this.route.snapshot.params['dataId'];
    this.userSpidData = {}
  }

}
