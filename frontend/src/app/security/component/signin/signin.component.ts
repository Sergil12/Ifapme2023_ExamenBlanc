import {Component, OnInit} from '@angular/core';
import {AuthService} from '@security/service/auth.service';
import {SigninPayload} from '@security/model';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  payload: SigninPayload = {username: '', password: ''}
  formGroup!: FormGroup;

  constructor(public auth: AuthService) {
  }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),

    })}

  signin() {
    if(this.formGroup.invalid){
      alert('formulaire invalid')
    }
    else{
      const payload: SigninPayload = this.formGroup.value //contiens les donner que j'enregistre dans le from group
      this.auth.signin(payload).subscribe()
      console.log(payload)
    }

  }
}
