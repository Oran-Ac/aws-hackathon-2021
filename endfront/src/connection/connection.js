import axios from 'axios';
axios.defaults.baseURL = "http://Soglobal-env.eba-gyvuqgty.us-east-1.elasticbeanstalk.com";
// AWS云服务通信
const AWS = require("aws-sdk");
AWS.config.region = "xxxxx";
AWS.config.credentials = new AWS.CognitoIdentityCredentials({
    IdentityPoolId: "xxxxxxxx",
});
// s3配置
let albumBucketName = "xxxx";
let s3 = new AWS.S3({
    apiVersion: "2006-03-01",
    params: { Bucket: albumBucketName },
});
// lambda配置
AWS.config.apiVersion = {
    lambda: "2015-03-31"
}
let lambda = new AWS.Lambda()
// 动态语言翻译
function getLanguageDyncmic(str, attr) {
    return lambda.invoke({
        FunctionName: "xxxxx",
        Payload: JSON.stringify({
            target_language: sessionStorage.getItem("Language"),
            descriptions: str[attr],
        })

    }, (e, data) => {
        str[attr] = JSON.parse(data.Payload).descriptions
    },
    )
}
// 动态货币单位翻译
function getCurrency(to) {
    return lambda.invoke({
        FunctionName: "transfer",
        Payload: JSON.stringify({
            from_currency: 'CNY',
            to_currency: to,
        }),
        InvocationType: 'RequestResponse',
    }).promise()
}
// 照片放云
async function postImgAWS(key, images) {
    s3.upload(
        {
            Bucket: "xxxx",
            Key: key,
            Body: images,
            ACL: "public-read",
        },
        (e) => {
            console.log("错误" + e);
        }
    );
}
// multipart/form-data post通信
async function postMultipart(url, fd) {
    try {
        let response = await axios({
            url: url,
            method: "post",
            headers: {
                "Content-Type": "multipart/form-data",
            },
            data: fd,
        });
        console.log(response);
        return response;
    } catch (e) {
        console.log("传输错误" + e);
    }
}
// multipart/form-data post通信
async function putMultipart(url, fd) {
    try {
        let response = await axios({
            url: url,
            method: "post",
            headers: {
                "Content-Type": "multipart/form-data",
            },
            data: fd,
        });
        console.log(response);
        return response;
    } catch (e) {
        console.log("传输错误" + e);
    }
}
async function getJson(url, params) {
    try {
        let response = await axios({
            url: url,
            method: "get",
            headers: {
                "Content-Type": "application/json",
            },
            params: params,
        });
        console.log(response);
        return response;
    } catch (e) {
        console.log("传输错误:" + e);
    }
}
async function postJson(url, data) {
    try {
        let response = await axios({
            url: url,
            method: "post",
            headers: {
                "Content-Type": "application/json",
            },
            data: data,
        });
        console.log(response);
        return response;
    } catch (e) {
        console.log("传输错误:" + e);
    }
}
async function putJson(url, data) {
    try {
        let response = await axios({
            url: url,
            method: "put",
            headers: {
                "Content-Type": "application/json",
            },
            data: data,
        });
        console.log(response);
        return response;
    } catch (e) {
        console.log("传输错误:" + e);
    }
}
async function deleteJson(url, data) {
    try {
        let response = await axios({
            url: url,
            method: "delete",
            headers: {
                "Content-Type": "application/json",
            },
            data: data,
        });
        console.log(response);
        return response;
    } catch (e) {
        console.log("传输错误:" + e);
    }
}
async function getLanguage(fileFloder) {
    try {
        let response = await axios({
            url: "xxxxxx" + fileFloder +
                sessionStorage.getItem("Language") +
                ".json",
            method: "get",
            headers: {
                "Content-Type": "application/json",
            },
        });
        console.log(response);
        return response;
    } catch (e) {
        console.log("传输错误:" + e);
    }
}
export default {
    postMultipart,
    postJson,
    getJson,
    postImgAWS,
    putJson,
    putMultipart,
    deleteJson,
    getLanguage,
    getCurrency,
    getLanguageDyncmic
}