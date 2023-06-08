import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SignupPayload} from "@security/model";
import {ApiResponse} from "@shared/model";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  payload: SignupPayload = {username: '', password: ''}
  formGroup!: FormGroup;
  private toaster: any;
  private auth: any;
  private ToastType: any;
  private passwordType!: string;
  constructor() { }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    })
  }
  signup(): void {
    if (this.formGroup.invalid) {
      this.toaster.show(this.ToastType.ERROR, 'page.signup.validation.desc-error', 'page.signup.validation.title-error', 6000);
    } else {
      this.payload = this.formGroup.value;

        this.auth.signup(this.formGroup.value).subscribe((response: ApiResponse) => {
          if (!response.result) {
            this.toaster.show(this.ToastType.ERROR, response.error_code, '', 6000);
          }
        })
    }
  }

  showPassword(): void {
    this.passwordType = (this.passwordType === 'password') ? 'text' : 'password';
  }

  private isUUID(code: any) {
    return false;
  }
}


