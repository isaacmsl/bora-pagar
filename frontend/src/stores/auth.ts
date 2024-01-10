import { UserService } from '@/services/UserService';
import type { GoogleUserInfo } from '@/types/GoogleUserInfo';
import { defineStore } from 'pinia';
import { decodeCredential, googleLogout } from 'vue3-google-login';
import router from '@/router';

const DEFAULT_TIMESTAMP_TO_MS = 1000;

const userService = new UserService();

const credentialKeyName = 'credential';

function normalizeGivenName(name : String) {
  return name.charAt(0) + name.slice(1).toLowerCase();
}

export const useAuthStore = defineStore('auth', {
  state: () => {
    return { user: undefined as GoogleUserInfo | undefined }
  },
  actions: {
    isCredentialNotExpired(credential : string) {
      const user = decodeCredential(credential) as GoogleUserInfo;
      return (user.exp * DEFAULT_TIMESTAMP_TO_MS) > Date.now();
    },
    setUserFromCredential(credential : string) {
      this.user = decodeCredential(credential) as GoogleUserInfo;
      this.user.given_name = normalizeGivenName(this.user.given_name);
    },
    googleLoginCallback(response : any) {
      localStorage.setItem(credentialKeyName, response.credential);
      this.setUserFromCredential(response.credential);
      userService.welcomeUser(response.credential);
    },
    getCredentialFromLocalStorage() : string {
      const credential = localStorage.getItem(credentialKeyName);
      if (credential) {
        try {
          if (this.isCredentialNotExpired(credential)) {
            this.setUserFromCredential(credential);
          } else {
            this.logout();
          }
        } catch (error) {
          this.logout();
        }
      }

      return String(credential);
    },
    logout() {
      localStorage.removeItem(credentialKeyName);
      this.user = undefined;
      googleLogout();
      router.push('/');
    },
    loggedIn() {
      return this.user != undefined;
    }
  },
})