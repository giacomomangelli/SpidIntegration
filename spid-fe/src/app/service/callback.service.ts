import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserSpidData} from '../model/userdata';

@Injectable({
  providedIn: 'root'
})
export class CallbackService {

  BASE_URL = environment.url;

  constructor(private readonly http: HttpClient) { }

  public getSpidUserData(userId: string): Observable<UserSpidData> {
    return this.http.get<UserSpidData>(this.BASE_URL + 'retrieve-data/' + userId);
  }
}
