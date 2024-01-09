import type { PageSubject } from '@/types/PageSubject';
import type { Subject } from '@/types/Subject';
import axios, { Axios, type AxiosResponse } from 'axios'
export class SubjectService {
  private axiosInstance: Axios;

  public constructor() {
    this.axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_URL + '/subjects',
    });
  }

  public async addInterestedUserByCode(credential: string, code: string): Promise<Subject> {
    const response = await this.axiosInstance.post<any, AxiosResponse<Subject>>('/interested', {}, {
      params: {
        code
      },
      headers: {
        credential
      }
    });

    return response.data;
  }

  public async removeInterestedUserByCode(credential: string, code: string): Promise<Subject> {
    const response = await this.axiosInstance.delete<any, AxiosResponse<Subject>>('/interested', {
      params: {
        code
      },
      headers: {
        credential
      }
    });

    return response.data;
  }

  public async getPage(page: Number = 1): Promise<PageSubject> {
    const response = await this.axiosInstance.get<any, AxiosResponse<PageSubject>>('/findAll', {
      params: {
        page
      }
    });
    return response.data;
  }
}