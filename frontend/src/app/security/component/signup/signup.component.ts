import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupPayload } from '@security/model';
import { AuthService } from '@security/service/auth.service';
import { ApiResponse } from '@shared/model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  payload: SignupPayload = {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
  };
  formGroup!: FormGroup;
  private toaster: any;
  private ToastType: any;
  private passwordType!: string;
  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      firstname: new FormControl('', [Validators.required]),
      lastname: new FormControl('', [Validators.required]),
    });
  }
  signup(): void {
    if (this.formGroup.invalid) {
      /*this.toaster.show(
        this.ToastType.ERROR,
        'page.signup.validation.desc-error',
        'page.signup.validation.title-error',
        6000
      );*/
      alert('formulaire invalid');
    } else {
      //this.payload = this.formGroup.value;
      const payload: SignupPayload = this.formGroup.value;

      this.auth.signup(payload).subscribe(
        (response: ApiResponse) => {
          console.log('res: ', response);
          if (!response.result) {
            /*this.toaster.show(
            this.ToastType.ERROR,
            response.error_code,
            '',
            6000
          );*/
            alert("Une erreur s'est produite");
          }
        },
        (err: Error) => {
          console.log('err: ', err);
        }
      );
    }
  }

  showPassword(): void {
    this.passwordType = this.passwordType === 'password' ? 'text' : 'password';
  }

  private isUUID(code: any) {
    return false;
  }

  goToSignin() {
    this.router.navigate(['account/signin']);
  }
}
