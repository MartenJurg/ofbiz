package ee.ttu.objectdistribution.services;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

import java.util.Arrays;
import java.util.Map;

public class ObjectDistributionServices {

    public static final String module = ObjectDistributionServices.class.getName();

    public static Map<String, Object> createObjectDistribution(DispatchContext dctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        try {
            Debug.log("[DEBUG]: " + context.toString() + context.keySet() + Arrays.toString(context.values().toArray()));
            GenericValue objectDistribution = delegator.makeValue("ObjectDistribution");
            // Auto generating next sequence of ObjectDistributionId primary key
            objectDistribution.setNextSeqId();
            // Setting up all non primary key field values from context map
            objectDistribution.setNonPKFields(context);
            // Creating record in database for ObjectDistribution entity for prepared value
            objectDistribution = delegator.create(objectDistribution);
            result.put("ObjectDistributionId", objectDistribution.getString("ObjectDistributionId"));
            Debug.log("Started ObjectDistributionService...");
        } catch (GenericEntityException e) {
            Debug.logError(e, module);
            Debug.log("Starting ObjectDistributionService Failed...");
            return ServiceUtil.returnError("Error in creating record in ObjectDistribution entity ........" + module);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
